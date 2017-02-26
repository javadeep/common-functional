package com.javadeep.functional.lang.control.base;


import com.javadeep.functional.lang.control.base.annotation.Value;
import com.javadeep.functional.lang.data.Tuple2;
import com.javadeep.functional.lang.function.Functions;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            return Stream.of(enumType.getDeclaredMethods())
                    .filter(method -> method.isAnnotationPresent(Value.class))
                    .flatMap(method -> Stream.of(enumValues)
                            .map((Functions.uncheckedFunction(v -> Tuple2.of(method.invoke(v), v)))))
                    .collect(Collectors.toMap(t -> computeKey(t.t1()), Tuple2::t2));

        } catch (Exception e) {
            return Collections.emptyMap();
        }
    }

    private static String computeKey(Object value) {
        return String.format("%s:%s", value.getClass(), value);
    }
}
