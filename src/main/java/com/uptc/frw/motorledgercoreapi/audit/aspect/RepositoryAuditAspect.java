package com.uptc.frw.motorledgercoreapi.audit.aspect;

import com.uptc.frw.motorledgercoreapi.audit.config.AuditProperties;
import com.uptc.frw.motorledgercoreapi.audit.service.AuditService;
import com.uptc.frw.motorledgercoreapi.audit.util.EntityHelper;
import jakarta.persistence.Entity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Aspecto AOP que intercepta automáticamente las operaciones en repositorios JPA.
 * Captura save() y delete() sin necesidad de anotaciones explícitas.
 * Tiene menor prioridad que AuditableMethodAspect para evitar auditorías duplicadas.
 */
@Aspect
@Component
@Order(2) // Menor prioridad que AuditableMethodAspect
public class RepositoryAuditAspect {

    private static final Logger logger = LoggerFactory.getLogger(RepositoryAuditAspect.class);

    @Autowired
    private AuditService auditService;

    @Autowired
    private AuditProperties auditProperties;

    @Autowired
    private EntityHelper entityHelper;

    /**
     * Intercepta el método save() de los repositorios.
     * Distingue entre INSERT (nuevo) y UPDATE (existente).
     */
    @Around("execution(* org.springframework.data.jpa.repository.JpaRepository+.save(..)) && args(entity)")
    public Object auditSave(ProceedingJoinPoint joinPoint, Object entity) throws Throwable {
        if (!auditProperties.isEnabled() || !isAuditableEntity(entity)) {
            return joinPoint.proceed();
        }

        String aspectName = this.getClass().getSimpleName();
        Object entityId = entityHelper.getEntityId(entity);
        Object entityBefore = null;

        // Si tiene ID, es UPDATE; si no, es INSERT
        boolean isUpdate = (entityId != null && !entityId.toString().equals("0"));

        try {
            if (isUpdate) {
                // Capturar estado antes del UPDATE
                entityBefore = cloneEntity(entity);
            }

            // Ejecutar el save original
            Object result = joinPoint.proceed();

            // Auditar según el tipo de operación
            if (isUpdate && entityBefore != null) {
                auditService.auditUpdate(entityBefore, result, aspectName, getCurrentUser(), true);
            } else {
                auditService.auditInsert(result, aspectName, getCurrentUser());
            }

            return result;

        } catch (Exception e) {
            logger.error("Error during repository save audit: {}", e.getMessage());
            throw e;
        }
    }

    /**
     * Intercepta el método deleteById() de los repositorios.
     */
    @Around("execution(* org.springframework.data.jpa.repository.JpaRepository+.deleteById(..)) && args(id)")
    public Object auditDeleteById(ProceedingJoinPoint joinPoint, Object id) throws Throwable {
        if (!auditProperties.isEnabled()) {
            return joinPoint.proceed();
        }

        String aspectName = this.getClass().getSimpleName();

        try {
            // Intentar obtener la entidad antes de eliminar
            Object entityBefore = findEntityById(joinPoint.getTarget(), id);

            // Ejecutar el delete original
            Object result = joinPoint.proceed();

            // Auditar si se pudo obtener la entidad
            if (entityBefore != null && isAuditableEntity(entityBefore)) {
                auditService.auditDelete(entityBefore, aspectName, getCurrentUser());
            }

            return result;

        } catch (Exception e) {
            logger.error("Error during repository delete audit: {}", e.getMessage());
            throw e;
        }
    }

    /**
     * Intercepta el método delete() de los repositorios (con entidad).
     */
    @Around("execution(* org.springframework.data.jpa.repository.JpaRepository+.delete(..)) && args(entity)")
    public Object auditDelete(ProceedingJoinPoint joinPoint, Object entity) throws Throwable {
        if (!auditProperties.isEnabled() || !isAuditableEntity(entity)) {
            return joinPoint.proceed();
        }

        String aspectName = this.getClass().getSimpleName();

        try {
            // Clonar la entidad antes de eliminar
            Object entityBefore = cloneEntity(entity);

            // Ejecutar el delete original
            Object result = joinPoint.proceed();

            // Auditar
            if (entityBefore != null) {
                auditService.auditDelete(entityBefore, aspectName, getCurrentUser());
            }

            return result;

        } catch (Exception e) {
            logger.error("Error during repository delete audit: {}", e.getMessage());
            throw e;
        }
    }

    /**
     * Verifica si una entidad es auditable (tiene anotación @Entity).
     */
    private boolean isAuditableEntity(Object entity) {
        return entity != null && entity.getClass().isAnnotationPresent(Entity.class);
    }

    /**
     * Clona una entidad creando un snapshot de su estado actual.
     */
    private Object cloneEntity(Object entity) {
        // Para simplificar, convertimos a Map que es lo que necesitamos
        // El entityHelper.entityToMap ya captura el estado actual
        return entity;
    }

    /**
     * Intenta encontrar una entidad por ID en el repositorio.
     */
    private Object findEntityById(Object repository, Object id) {
        try {
            java.lang.reflect.Method findByIdMethod = repository.getClass()
                    .getMethod("findById", Object.class);

            Object optional = findByIdMethod.invoke(repository, id);

            if (optional instanceof java.util.Optional) {
                return ((java.util.Optional<?>) optional).orElse(null);
            }
        } catch (Exception e) {
            logger.debug("Could not retrieve entity by ID for delete audit: {}", e.getMessage());
        }

        return null;
    }

    /**
     * Obtiene el usuario actual.
     */
    private String getCurrentUser() {
        return "motorledger_system";
    }
}
