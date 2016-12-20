package com.javadeep.functional.lang.data;

import com.javadeep.functional.lang.function.CheckedSupplier;

import java.util.Objects;

/**
 * An implementation of Try control.
 *
 * @param <T> Value Type in the case of success
 * @author baojie
 * @since 1.0.0
 */
public interface Try<T> {

    /**
     * Creates a Try of a CheckedSupplier.
     *
     * @param supplier A checked supplier
     * @param <T> Component type
     * @return {@code Success(supplier.get())} if no exception occurs, otherwise {@code Failure(throwable)} if an
     * exception occurs calling {@code supplier.get()}.
     */
    static <T> Try<T> of(CheckedSupplier<? extends T> supplier) {
        try {
            return new Success<>(supplier.get());
        } catch (Throwable t) {
            return new Failure<>(t);
        }
    }

    /**
     * A succeeded Try.
     *
     * @param <T> component type of this Success
     * @author baojie
     * @since 1.0.0
     */
    final class Success<T> implements Try<T> {

        private final T value;

        private Success(T value) {
            this.value = value;
        }
    }

    /**
     * A failed Try.
     *
     * @param <T> component type of this Failure
     * @author baojie
     * @since 1.0.0
     */
    final class Failure<T> implements Try<T> {

        private final Throwable cause;

        private Failure(Throwable exception) {
            Objects.requireNonNull(exception, "exception is null");
            this.cause = exception;
        }
    }
}
