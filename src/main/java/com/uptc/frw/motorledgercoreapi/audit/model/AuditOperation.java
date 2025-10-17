package com.uptc.frw.motorledgercoreapi.audit.model;

/**
 * Enumeración de los tipos de operaciones auditables.
 */
public enum AuditOperation {
    /**
     * Operación de inserción de un nuevo registro
     */
    INSERT,

    /**
     * Operación de actualización de un registro existente
     */
    UPDATE,

    /**
     * Operación de eliminación de un registro
     */
    DELETE
}
