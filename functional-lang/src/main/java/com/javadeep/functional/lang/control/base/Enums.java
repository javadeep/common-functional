package com.javadeep.functional.lang.control.base;


import com.javadeep.functional.lang.control.base.annotation.Value;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Tools for Enum
 *
 * @author baojie
 * @since 1.0.0
 */
public final class Enums {

    private static ConcurrentHashMap<String, Map<String, Enum<?>>> cache = new ConcurrentHashMap<>();

    /**
     * Get the enum instance from {@code value}.
     *
     * @param value The value.
     * @param enumType The class of enum.
     * @param <E> The enum Type.
     * @return The enum instance.
     */
    @SuppressWarnings("unchecked")
    public static <E extends Enum<E>> Optional<E> find(Object value, Class<E> enumType) {
        Objects.requireNonNull(value, "value is null");
        Objects.requireNonNull(enumType, "enumType is null");

        Map<String, ?> resultMap = cache.computeIfAbsent(enumType.getName(),
                s -> (Map<String, Enum<?>>) computeResultMap(enumType));
        return Optional.ofNullable((E) resultMap.get(computeKey(value)));
    }


    private static <E extends Enum<E>> Map<String, E> computeResultMap(Class<E> enumType) {

        E[] enumValues = enumType.getEnumConstants();

        try {
            Map<String, E> resultMap = new HashMap<>();
            for (Method method : enumType.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Value.class)) {
                    for (E enumValue : enumValues) {
                        Object value = method.invoke(enumValue);
                        resultMap.put(computeKey(value), enumValue);
                    }
                }
            }
            return resultMap;
        } catch (Exception e) {
            return Collections.emptyMap();
        }
    }

    private static String computeKey(Object value) {
        return String.format("%s:%s", value.getClass(), value);
    }
}
