package com.javadeep.functional.lang.control.base;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Supplier;

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

    public static void checkNotEmpty(Collection<?> reference) {
        Objects.requireNonNull(reference);
        if (reference.size() <= 0) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkNotEmpty(Collection<?> reference, String message) {
        Objects.requireNonNull(message);
        Objects.requireNonNull(reference, message);
        if (reference.size() <= 0) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void checkNotEmpty(Collection<?> reference, Supplier<String> messageSupplier) {
        Objects.requireNonNull(messageSupplier);
        Objects.requireNonNull(reference, messageSupplier);
        if (reference.size() <= 0) {
            throw new IllegalArgumentException(messageSupplier.get());
        }
    }

    public static void checkNotEmpty(Object[] reference) {
        Objects.requireNonNull(reference);
        if (reference.length <= 0) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkNotEmpty(Object[] reference, String message) {
        Objects.requireNonNull(message);
        Objects.requireNonNull(reference, message);
        if (reference.length <= 0) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void checkNotEmpty(Object[] reference, Supplier<String> messageSupplier) {
        Objects.requireNonNull(messageSupplier);
        Objects.requireNonNull(reference, messageSupplier);
        if (reference.length <= 0) {
            throw new IllegalArgumentException(messageSupplier.get());
        }
    }
}
