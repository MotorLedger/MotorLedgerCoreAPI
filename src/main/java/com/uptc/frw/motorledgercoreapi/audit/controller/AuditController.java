package com.uptc.frw.motorledgercoreapi.audit.controller;

import com.uptc.frw.motorledgercoreapi.audit.model.AuditLog;
import com.uptc.frw.motorledgercoreapi.audit.model.AuditOperation;
import com.uptc.frw.motorledgercoreapi.audit.repository.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

/**
 * Controlador REST para consultar los logs de auditoría.
 * Proporciona endpoints para buscar auditorías por diferentes criterios.
 */
@RestController
@RequestMapping("/api/audit")
public class AuditController {

    @Autowired
    private AuditLogRepository auditLogRepository;

    /**
     * Obtiene todos los logs de auditoría.
     * GET /api/audit
     */
    @GetMapping
    public ResponseEntity<List<AuditLog>> getAllAuditLogs() {
        return ResponseEntity.ok(auditLogRepository.findAll());
    }

    /**
     * Obtiene logs de auditoría por entidad.
     * GET /api/audit/entity/{entityName}
     * <p>
     * Ejemplo: /api/audit/entity/SALES
     */
    @GetMapping("/entity/{entityName}")
    public ResponseEntity<List<AuditLog>> getAuditLogsByEntity(@PathVariable String entityName) {
        return ResponseEntity.ok(auditLogRepository.findByEntity(entityName.toUpperCase()));
    }

    /**
     * Obtiene el historial completo de una entidad específica por su ID.
     * GET /api/audit/entity/{entityName}/{entityId}
     * <p>
     * Ejemplo: /api/audit/entity/SALES/12345
     */
    @GetMapping("/entity/{entityName}/{entityId}")
    public ResponseEntity<List<AuditLog>> getEntityHistory(
            @PathVariable String entityName,
            @PathVariable Long entityId) {
        return ResponseEntity.ok(
                auditLogRepository.findByEntityAndEntityPk(entityName.toUpperCase(), entityId)
        );
    }

    /**
     * Obtiene logs por tipo de operación.
     * GET /api/audit/operation/{operation}
     * <p>
     * Ejemplo: /api/audit/operation/INSERT
     */
    @GetMapping("/operation/{operation}")
    public ResponseEntity<List<AuditLog>> getAuditLogsByOperation(
            @PathVariable AuditOperation operation) {
        return ResponseEntity.ok(auditLogRepository.findByOperation(operation));
    }

    /**
     * Obtiene logs por usuario.
     * GET /api/audit/user/{username}
     * <p>
     * Ejemplo: /api/audit/user/john_doe
     */
    @GetMapping("/user/{username}")
    public ResponseEntity<List<AuditLog>> getAuditLogsByUser(@PathVariable String username) {
        return ResponseEntity.ok(auditLogRepository.findByUser(username));
    }

    /**
     * Obtiene logs por rango de fechas.
     * GET /api/audit/range?start=2025-01-01T00:00:00Z&end=2025-12-31T23:59:59Z
     */
    @GetMapping("/range")
    public ResponseEntity<List<AuditLog>> getAuditLogsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant end) {
        return ResponseEntity.ok(auditLogRepository.findByTimestampBetween(start, end));
    }

    /**
     * Obtiene logs de una entidad en un rango de fechas.
     * GET /api/audit/entity/{entityName}/range?start=...&end=...
     */
    @GetMapping("/entity/{entityName}/range")
    public ResponseEntity<List<AuditLog>> getEntityAuditLogsByDateRange(
            @PathVariable String entityName,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant end) {
        return ResponseEntity.ok(
                auditLogRepository.findByEntityAndTimestampBetween(entityName.toUpperCase(), start, end)
        );
    }

    /**
     * Obtiene un log de auditoría específico por ID.
     * GET /api/audit/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<AuditLog> getAuditLogById(@PathVariable String id) {
        return auditLogRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
