package com.uptc.frw.motorledgercoreapi.audit.aspect;

import com.uptc.frw.motorledgercoreapi.audit.annotation.Auditable;
import com.uptc.frw.motorledgercoreapi.audit.service.AuditService;
import com.uptc.frw.motorledgercoreapi.audit.util.EntityHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Aspecto AOP que intercepta métodos anotados con @Auditable.
 * Captura las operaciones CRUD y delega la auditoría al AuditService.
 */
@Aspect
@Component
public class AuditableMethodAspect {

    private static final Logger logger = LoggerFactory.getLogger(AuditableMethodAspect.class);

    @Autowired
    private AuditService auditService;

    @Autowired
    private EntityHelper entityHelper;

    /**
     * Intercepta métodos anotados con @Auditable.
     * Determina el tipo de operación y audita antes/después de la ejecución.
     */
    @Around("@annotation(com.uptc.frw.motorledgercoreapi.audit.annotation.Auditable)")
    public Object auditMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Auditable auditable = method.getAnnotation(Auditable.class);

        String aspectName = this.getClass().getSimpleName();
        String operationType = auditable.operation();

        // Detectar automáticamente el tipo de operación si es AUTO
        if ("AUTO".equals(operationType)) {
            operationType = detectOperationType(method.getName());
        }

        Object result;
        Object entityBefore = null;

        try {
            // Para UPDATE, capturar el estado antes
            if ("UPDATE".equals(operationType) && joinPoint.getArgs().length > 0) {
                Object arg = joinPoint.getArgs()[0];
                if (arg != null) {
                    // Obtener el ID y buscar la entidad antes del cambio
                    Object id = entityHelper.getEntityId(arg);
                    if (id != null) {
                        entityBefore = findEntityById(joinPoint.getTarget(), id);
                    }
                }
            }

            // Para DELETE, capturar la entidad antes de eliminar
            if ("DELETE".equals(operationType) && joinPoint.getArgs().length > 0) {
                Object arg = joinPoint.getArgs()[0];
                if (arg instanceof Number) {
                    // Si el argumento es un ID, buscar la entidad
                    entityBefore = findEntityById(joinPoint.getTarget(), arg);
                } else {
                    // Si es la entidad completa
                    entityBefore = arg;
                }
            }

            // Ejecutar el método original
            result = joinPoint.proceed();

            // Auditar después de la ejecución exitosa
            auditAfterExecution(operationType, entityBefore, result, aspectName, auditable);

        } catch (Exception e) {
            logger.error("Error during audited method execution: {}", e.getMessage());
            throw e;
        }

        return result;
    }

    /**
     * Audita después de la ejecución del método.
     */
    private void auditAfterExecution(String operationType, Object entityBefore,
                                     Object result, String aspectName, Auditable auditable) {
        String user = getCurrentUser();

        switch (operationType) {
            case "INSERT":
                if (result != null) {
                    auditService.auditInsert(result, aspectName, user);
                }
                break;

            case "UPDATE":
                if (result != null && entityBefore != null) {
                    auditService.auditUpdate(entityBefore, result, aspectName, user, auditable.includeDiff());
                }
                break;

            case "DELETE":
                if (entityBefore != null) {
                    auditService.auditDelete(entityBefore, aspectName, user);
                }
                break;

            default:
                logger.warn("Unknown operation type: {}", operationType);
        }
    }

    /**
     * Detecta el tipo de operación basándose en el nombre del método.
     */
    private String detectOperationType(String methodName) {
        String lowerName = methodName.toLowerCase();

        if (lowerName.contains("save") || lowerName.contains("create") || lowerName.contains("insert")) {
            return "INSERT";
        } else if (lowerName.contains("update") || lowerName.contains("modify") || lowerName.contains("edit")) {
            return "UPDATE";
        } else if (lowerName.contains("delete") || lowerName.contains("remove")) {
            return "DELETE";
        }

        return "INSERT"; // Default
    }

    /**
     * Intenta encontrar una entidad por ID usando el repositorio del servicio.
     * Esto es necesario para capturar el estado BEFORE en operaciones UPDATE/DELETE.
     */
    private Object findEntityById(Object target, Object id) {
        try {
            // Buscar método getById o findById en el servicio
            Method[] methods = target.getClass().getDeclaredMethods();

            for (Method method : methods) {
                String methodName = method.getName().toLowerCase();
                if ((methodName.contains("getbyid") || methodName.contains("findbyid"))
                        && method.getParameterCount() == 1) {
                    method.setAccessible(true);
                    return method.invoke(target, id);
                }
            }
        } catch (Exception e) {
            logger.debug("Could not retrieve entity before state: {}", e.getMessage());
        }

        return null;
    }

    /**
     * Obtiene el usuario actual del contexto de seguridad.
     * Por ahora retorna un usuario por defecto.
     * TODO: Integrar con Spring Security cuando esté implementado.
     */
    private String getCurrentUser() {
        // En un entorno real, obtener desde SecurityContextHolder
        // SecurityContext context = SecurityContextHolder.getContext();
        // Authentication auth = context.getAuthentication();
        // return auth != null ? auth.getName() : "system_user";

        return "motorledger_system";
    }
}
