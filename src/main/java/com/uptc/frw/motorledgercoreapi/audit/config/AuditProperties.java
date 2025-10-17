package com.uptc.frw.motorledgercoreapi.audit.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Propiedades de configuración para el sistema de auditoría.
 * Se cargan desde application.properties con prefijo "audit".
 */
@Configuration
@ConfigurationProperties(prefix = "audit")
public class AuditProperties {

    /**
     * Habilita o deshabilita el sistema de auditoría
     */
    private boolean enabled = true;

    /**
     * Indica si la auditoría se ejecuta de forma asíncrona
     */
    private boolean async = true;

    /**
     * Fuente de datos origen (ej: "SQL", "NoSQL")
     */
    private String source = "MotorLedgerCoreAPI.SQL";

    /**
     * Usuario por defecto para auditorías del sistema
     */
    private String defaultUser = "motorledger_system";

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAsync() {
        return async;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDefaultUser() {
        return defaultUser;
    }

    public void setDefaultUser(String defaultUser) {
        this.defaultUser = defaultUser;
    }
}
