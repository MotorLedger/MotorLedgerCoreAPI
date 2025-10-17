package com.uptc.frw.motorledgercoreapi.audit.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.Map;

/**
 * Documento de auditoría que se persiste en MongoDB.
 * Representa el registro completo de una operación CRUD sobre una entidad.
 */
@Document(collection = "audit_log")
public class AuditLog {

    @Id
    private String id;

    /**
     * Fuente de los datos (ej: "SQL", "NoSQL")
     */
    @Field("source")
    private String source;

    /**
     * Nombre de la entidad auditada (ej: "SALES", "BRANDS")
     */
    @Field("entity")
    private String entity;

    /**
     * Clave primaria de la entidad auditada
     */
    @Field("entity_pk")
    private Object entityPk;

    /**
     * Tipo de operación: INSERT, UPDATE, DELETE
     */
    @Field("operation")
    private AuditOperation operation;

    /**
     * Usuario que ejecutó la operación
     */
    @Field("user")
    private String user;

    /**
     * Timestamp de la operación
     */
    @Field("timestamp")
    private Instant timestamp;

    /**
     * Estado completo de la entidad ANTES del cambio (null para INSERT)
     */
    @Field("before")
    private Map<String, Object> before;

    /**
     * Estado completo de la entidad DESPUÉS del cambio (null para DELETE)
     */
    @Field("after")
    private Map<String, Object> after;

    /**
     * Diferencias específicas entre before y after (opcional)
     */
    @Field("diff")
    private Map<String, Object> diff;

    /**
     * Nombre del aspecto que generó esta auditoría
     */
    @Field("aspect")
    private String aspect;

    /**
     * Información adicional contextual
     */
    @Field("metadata")
    private Map<String, Object> metadata;

    public AuditLog() {
        this.timestamp = Instant.now();
    }

    // Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public Object getEntityPk() {
        return entityPk;
    }

    public void setEntityPk(Object entityPk) {
        this.entityPk = entityPk;
    }

    public AuditOperation getOperation() {
        return operation;
    }

    public void setOperation(AuditOperation operation) {
        this.operation = operation;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, Object> getBefore() {
        return before;
    }

    public void setBefore(Map<String, Object> before) {
        this.before = before;
    }

    public Map<String, Object> getAfter() {
        return after;
    }

    public void setAfter(Map<String, Object> after) {
        this.after = after;
    }

    public Map<String, Object> getDiff() {
        return diff;
    }

    public void setDiff(Map<String, Object> diff) {
        this.diff = diff;
    }

    public String getAspect() {
        return aspect;
    }

    public void setAspect(String aspect) {
        this.aspect = aspect;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "AuditLog{" +
                "id='" + id + '\'' +
                ", source='" + source + '\'' +
                ", entity='" + entity + '\'' +
                ", entityPk=" + entityPk +
                ", operation=" + operation +
                ", user='" + user + '\'' +
                ", timestamp=" + timestamp +
                ", aspect='" + aspect + '\'' +
                '}';
    }
}
