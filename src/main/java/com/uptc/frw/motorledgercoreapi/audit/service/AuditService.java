package com.uptc.frw.motorledgercoreapi.audit.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uptc.frw.motorledgercoreapi.audit.config.AuditProperties;
import com.uptc.frw.motorledgercoreapi.audit.model.AuditLog;
import com.uptc.frw.motorledgercoreapi.audit.model.AuditOperation;
import com.uptc.frw.motorledgercoreapi.audit.repository.AuditLogRepository;
import com.uptc.frw.motorledgercoreapi.audit.util.EntityHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Servicio centralizado para gestionar la auditoría de operaciones.
 * Proporciona métodos para crear y persistir logs de auditoría en MongoDB.
 */
@Service
public class AuditService {

    private static final Logger logger = LoggerFactory.getLogger(AuditService.class);

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Autowired
    private AuditProperties auditProperties;

    @Autowired
    @Qualifier("auditObjectMapper")
    private ObjectMapper objectMapper;

    @Autowired
    private EntityHelper entityHelper;

    /**
     * Audita una operación INSERT (creación de entidad).
     *
     * @param entity     Objeto de la entidad creada
     * @param aspectName Nombre del aspecto que genera la auditoría
     * @param user       Usuario que ejecutó la operación
     */
    @Async("auditTaskExecutor")
    public void auditInsert(Object entity, String aspectName, String user) {
        if (!auditProperties.isEnabled()) {
            return;
        }

        try {
            AuditLog auditLog = createBaseAuditLog(entity, AuditOperation.INSERT, aspectName, user);
            auditLog.setBefore(null);
            auditLog.setAfter(entityHelper.entityToMap(entity));

            save(auditLog);
            logger.debug("Audit INSERT saved for entity: {}", auditLog.getEntity());
        } catch (Exception e) {
            logger.error("Error auditing INSERT operation", e);
        }
    }

    /**
     * Audita una operación UPDATE (actualización de entidad).
     *
     * @param entityBefore Estado de la entidad antes del cambio
     * @param entityAfter  Estado de la entidad después del cambio
     * @param aspectName   Nombre del aspecto que genera la auditoría
     * @param user         Usuario que ejecutó la operación
     * @param includeDiff  Si se debe calcular el diff
     */
    @Async("auditTaskExecutor")
    public void auditUpdate(Object entityBefore, Object entityAfter, String aspectName,
                            String user, boolean includeDiff) {
        if (!auditProperties.isEnabled()) {
            return;
        }

        try {
            AuditLog auditLog = createBaseAuditLog(entityAfter, AuditOperation.UPDATE, aspectName, user);

            Map<String, Object> beforeMap = entityHelper.entityToMap(entityBefore);
            Map<String, Object> afterMap = entityHelper.entityToMap(entityAfter);

            auditLog.setBefore(beforeMap);
            auditLog.setAfter(afterMap);

            if (includeDiff) {
                auditLog.setDiff(calculateDiff(beforeMap, afterMap));
            }

            save(auditLog);
            logger.debug("Audit UPDATE saved for entity: {}", auditLog.getEntity());
        } catch (Exception e) {
            logger.error("Error auditing UPDATE operation", e);
        }
    }

    /**
     * Audita una operación DELETE (eliminación de entidad).
     *
     * @param entity     Objeto de la entidad eliminada
     * @param aspectName Nombre del aspecto que genera la auditoría
     * @param user       Usuario que ejecutó la operación
     */
    @Async("auditTaskExecutor")
    public void auditDelete(Object entity, String aspectName, String user) {
        if (!auditProperties.isEnabled()) {
            return;
        }

        try {
            AuditLog auditLog = createBaseAuditLog(entity, AuditOperation.DELETE, aspectName, user);
            auditLog.setBefore(entityHelper.entityToMap(entity));
            auditLog.setAfter(null);

            save(auditLog);
            logger.debug("Audit DELETE saved for entity: {}", auditLog.getEntity());
        } catch (Exception e) {
            logger.error("Error auditing DELETE operation", e);
        }
    }

    /**
     * Crea un AuditLog base con la información común.
     */
    private AuditLog createBaseAuditLog(Object entity, AuditOperation operation,
                                        String aspectName, String user) {
        AuditLog auditLog = new AuditLog();
        auditLog.setSource(auditProperties.getSource());
        auditLog.setEntity(entityHelper.getEntityName(entity));
        auditLog.setEntityPk(entityHelper.getEntityId(entity));
        auditLog.setOperation(operation);
        auditLog.setUser(user != null ? user : auditProperties.getDefaultUser());
        auditLog.setTimestamp(Instant.now());
        auditLog.setAspect(aspectName);

        return auditLog;
    }

    /**
     * Calcula las diferencias entre el estado anterior y posterior.
     *
     * @param before Mapa del estado anterior
     * @param after  Mapa del estado posterior
     * @return Mapa con solo los campos que cambiaron
     */
    private Map<String, Object> calculateDiff(Map<String, Object> before, Map<String, Object> after) {
        Map<String, Object> diff = new HashMap<>();

        if (before == null || after == null) {
            return diff;
        }

        after.forEach((key, afterValue) -> {
            Object beforeValue = before.get(key);

            // Si el valor cambió, lo agregamos al diff
            if (!isEqual(beforeValue, afterValue)) {
                Map<String, Object> change = new HashMap<>();
                change.put("from", beforeValue);
                change.put("to", afterValue);
                diff.put(key, change);
            }
        });

        return diff;
    }

    /**
     * Compara dos valores de forma segura.
     */
    private boolean isEqual(Object obj1, Object obj2) {
        if (obj1 == null && obj2 == null) {
            return true;
        }
        if (obj1 == null || obj2 == null) {
            return false;
        }
        return obj1.equals(obj2);
    }

    /**
     * Persiste el log de auditoría en MongoDB.
     * Maneja errores de forma silenciosa para no afectar el flujo principal.
     */
    private void save(AuditLog auditLog) {
        try {
            auditLogRepository.save(auditLog);
        } catch (Exception e) {
            logger.error("Failed to save audit log to MongoDB: {}", e.getMessage(), e);
            // No propagamos la excepción para no afectar la operación principal
        }
    }

    /**
     * Busca logs de auditoría por entidad y PK.
     */
    public java.util.List<AuditLog> getAuditHistory(String entity, Object entityPk) {
        return auditLogRepository.findByEntityAndEntityPk(entity, entityPk);
    }
}
