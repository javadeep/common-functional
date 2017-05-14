package com.javadeep.functional.lang.control.base;


import com.javadeep.functional.lang.control.base.annotation.Value;
import com.javadeep.functional.lang.data.Tuple2;
import com.javadeep.functional.lang.function.Functions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Tools for Enum
 *
 * @author baojie
 * @since 1.0.0
 */
public final class Enums {

    private Enums() {
        throw new UnsupportedOperationException();
    }

    private static Map<String, Map<String, Enum<?>>> cache = new HashMap<>();

    /**
     * Get the enum instance from {@code value}.
     *
     * @param value The value.
     * @param enumType The class of enum.
     * @param <E> The enum Type.
     * @return The enum instance.
     * @throws NullPointerException if {@code value} or {@code enumType} is null
     */
    @SuppressWarnings("unchecked")
    public static <E extends Enum<E>> Optional<E> find(Object value, Class<E> enumType) {
        Objects.requireNonNull(value, "value is null");
        Objects.requireNonNull(enumType, "enumType is null");

        if (!cache.containsKey(enumType.getName())) {
            computeAndSet(enumType);
        }
        return Optional.ofNullable((E) cache.get(enumType.getName()).get(computeKey(value)));
    }

    private synchronized static <E extends Enum<E>> void computeAndSet(Class<E> enumType) {

        if (cache.containsKey(enumType.getName())) {
            return;
        }

        E[] enumValues = enumType.getEnumConstants();

        try {
            cache.put(enumType.getName(), Stream.of(enumType.getDeclaredMethods())
                    .filter(method -> method.isAnnotationPresent(Value.class))
                    .flatMap(method -> Stream.of(enumValues)
                            .map((Functions.uncheckedFunction(v -> Tuple2.of(method.invoke(v), v)))))
                    .collect(Collectors.toMap(t -> computeKey(t.t1()), Tuple2::t2, (a, b) -> a)));

        } catch (Exception e) {
            cache.put(enumType.getName(), Collections.emptyMap());
        }
    }

    private static String computeKey(Object value) {
        return String.format("%s:%s", value.getClass(), value);
    }
}
