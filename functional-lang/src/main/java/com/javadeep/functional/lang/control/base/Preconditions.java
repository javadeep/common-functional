package com.javadeep.functional.lang.control.base;

import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Help a method or constructor check whether it was invoked correctly.
 *
 * @author baojie
 * @since 1.0.0
 */
public final class Preconditions {

    private Preconditions() {
        throw new UnsupportedOperationException();
    }

    /**
     * Ensures the truth of an expression involving one or more parameters to the calling method.
     *
     * @param expression A boolean expression.
     * @throws IllegalArgumentException if {@code expression} is false.
     */
    public static void checkArgument(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Ensures the truth of an expression involving one or more parameters to the calling method.
     *
     * @param expression A boolean expression.
     * @param message The exception message to use if the check fails.
     * @throws NullPointerException if {@code message} is null
     */
    public static void checkArgument(boolean expression, String message) {
        Objects.requireNonNull(message);
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Ensures the truth of an expression involving one or more parameters to the calling method.
     *
     * @param expression A boolean expression.
     * @param messageSupplier A supplier of the exception message to use if the check fails.
     * @throws NullPointerException if {@code messageSupplier} is null
     */
    public static void checkArgument(boolean expression, Supplier<String> messageSupplier) {
        Objects.requireNonNull(messageSupplier);
        if (!expression) {
            throw new IllegalArgumentException(messageSupplier.get());
        }
    }

    public static void checkNotEmpty(Stream<? extends Object> reference) {
        Objects.requireNonNull(reference);
        if (reference.count() <= 0) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkNotEmpty(Stream<? extends Object> reference, String message) {
        Objects.requireNonNull(message);
        Objects.requireNonNull(reference, message);
        if (reference.count() <= 0) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void checkNotEmpty(Stream<? extends Object> reference, Supplier<String> messageSupplier) {
        Objects.requireNonNull(messageSupplier);
        Objects.requireNonNull(reference, messageSupplier);
        if (reference.count() <= 0) {
            throw new IllegalArgumentException(messageSupplier.get());
        }
    }
}
