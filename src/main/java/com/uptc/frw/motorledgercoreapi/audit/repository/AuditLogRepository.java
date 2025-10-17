package com.uptc.frw.motorledgercoreapi.audit.repository;

import com.uptc.frw.motorledgercoreapi.audit.model.AuditLog;
import com.uptc.frw.motorledgercoreapi.audit.model.AuditOperation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

/**
 * Repositorio para acceder a los logs de auditoría en MongoDB.
 */
@Repository
public interface AuditLogRepository extends MongoRepository<AuditLog, String> {

    /**
     * Busca logs de auditoría por entidad
     */
    List<AuditLog> findByEntity(String entity);

    /**
     * Busca logs de auditoría por entidad y PK
     */
    List<AuditLog> findByEntityAndEntityPk(String entity, Object entityPk);

    /**
     * Busca logs de auditoría por operación
     */
    List<AuditLog> findByOperation(AuditOperation operation);

    /**
     * Busca logs de auditoría por usuario
     */
    List<AuditLog> findByUser(String user);

    /**
     * Busca logs de auditoría por rango de fechas
     */
    List<AuditLog> findByTimestampBetween(Instant start, Instant end);

    /**
     * Busca logs de auditoría por entidad y rango de fechas
     */
    List<AuditLog> findByEntityAndTimestampBetween(String entity, Instant start, Instant end);
}
