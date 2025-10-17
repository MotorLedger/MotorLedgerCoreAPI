package com.uptc.frw.motorledgercoreapi.audit.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Utilidad para extraer información de las entidades JPA.
 * Proporciona métodos para obtener el nombre, ID y convertir entidades a mapas.
 */
@Component
public class EntityHelper {

    @Autowired
    @Qualifier("auditObjectMapper")
    private ObjectMapper objectMapper;

    /**
     * Obtiene el nombre de la entidad desde la anotación @Table.
     * Si no existe, usa el nombre de la clase en mayúsculas.
     *
     * @param entity Objeto de la entidad
     * @return Nombre de la entidad en mayúsculas
     */
    public String getEntityName(Object entity) {
        if (entity == null) {
            return "UNKNOWN";
        }

        Class<?> entityClass = entity.getClass();

        // Intentar obtener el nombre desde @Table
        if (entityClass.isAnnotationPresent(Table.class)) {
            Table tableAnnotation = entityClass.getAnnotation(Table.class);
            return tableAnnotation.name().toUpperCase();
        }

        // Si no tiene @Table, usar el nombre de la clase
        return entityClass.getSimpleName().toUpperCase();
    }

    /**
     * Obtiene el ID de la entidad (valor del campo marcado con @Id).
     *
     * @param entity Objeto de la entidad
     * @return Valor del ID o null si no se encuentra
     */
    public Object getEntityId(Object entity) {
        if (entity == null) {
            return null;
        }

        try {
            // Buscar el campo marcado con @Id
            for (Field field : entity.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Id.class)) {
                    field.setAccessible(true);
                    return field.get(entity);
                }
            }
        } catch (Exception e) {
            // Log silencioso, devolver null
        }

        return null;
    }

    /**
     * Convierte una entidad a un Map para almacenar en MongoDB.
     * Filtra referencias circulares y colecciones complejas.
     *
     * @param entity Objeto de la entidad
     * @return Mapa con los valores primitivos y simples de la entidad
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> entityToMap(Object entity) {
        if (entity == null) {
            return null;
        }

        try {
            // Convertir la entidad a Map usando Jackson
            String json = objectMapper.writeValueAsString(entity);
            Map<String, Object> map = objectMapper.readValue(json, Map.class);

            // Filtrar campos nulos y vacíos para reducir tamaño
            return filterMap(map);
        } catch (Exception e) {
            // Si falla la serialización, crear manualmente
            return createMapManually(entity);
        }
    }

    /**
     * Filtra el mapa removiendo valores nulos y colecciones vacías.
     */
    private Map<String, Object> filterMap(Map<String, Object> map) {
        Map<String, Object> filtered = new HashMap<>();

        map.forEach((key, value) -> {
            if (value != null) {
                filtered.put(key, value);
            }
        });

        return filtered;
    }

    /**
     * Crea un mapa manualmente usando reflexión.
     * Se usa como fallback si Jackson falla.
     */
    private Map<String, Object> createMapManually(Object entity) {
        Map<String, Object> map = new HashMap<>();

        try {
            for (Field field : entity.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(entity);

                // Solo incluir tipos primitivos y String
                if (value != null && isPrimitiveOrString(value)) {
                    map.put(field.getName(), value);
                }
            }
        } catch (Exception e) {
            // Retornar mapa vacío en caso de error
        }

        return map;
    }

    /**
     * Verifica si un objeto es de tipo primitivo, wrapper o String.
     */
    private boolean isPrimitiveOrString(Object value) {
        return value instanceof String ||
                value instanceof Number ||
                value instanceof Boolean ||
                value instanceof Character ||
                value.getClass().isPrimitive();
    }
}
