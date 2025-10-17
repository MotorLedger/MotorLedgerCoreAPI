package com.uptc.frw.motorledgercoreapi.audit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotación para marcar métodos que deben ser auditados.
 * Se aplica a nivel de método en los servicios.
 * <p>
 * Ejemplo de uso:
 * <pre>
 * {@code
 * @Auditable(operation = AuditOperation.INSERT)
 * public Sale saveSale(Sale sale) {
 *     // código del método
 * }
 * }
 * </pre>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Auditable {

    /**
     * Tipo de operación que se está auditando.
     * Por defecto se intenta detectar automáticamente.
     */
    String operation() default "AUTO";

    /**
     * Nombre de la entidad auditada.
     * Si no se especifica, se intenta extraer del tipo de retorno.
     */
    String entity() default "";

    /**
     * Indica si se debe calcular el diff entre before y after.
     * Útil para operaciones UPDATE donde quieres ver qué cambió.
     */
    boolean includeDiff() default true;

    /**
     * Indica si la auditoría debe ser asíncrona.
     * Por defecto usa la configuración global.
     */
    boolean async() default true;
}
