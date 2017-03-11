package com.javadeep.functional.lang.control.base;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Tools for Optional
 *
 * @author baojie
 * @since 1.0.0
 */
public final class Optionals {

    private Optionals() {
        throw new UnsupportedOperationException();
    }

    /**
     * Consumes either the present or not side of this disjunction.
     *
     * @param value The optional value.
     * @param consumer Consumes the value if value is present.
     * @param runnable execute the {@code runnable} if value is not present.
     * @param <T> The type of value.
     * @throws NullPointerException if {@code value} or {@code consumer} or {@code runnable} is null
     */
    public static <T> void consume(Optional<T> value, Consumer<T> consumer, Runnable runnable) {

        Objects.requireNonNull(value, "value is null");
        Objects.requireNonNull(consumer, "consumer is null");
        Objects.requireNonNull(runnable, "runnable is null");

        if (value.isPresent()) {
            consumer.accept(value.get());
        } else {
            runnable.run();
        }
    }
}
