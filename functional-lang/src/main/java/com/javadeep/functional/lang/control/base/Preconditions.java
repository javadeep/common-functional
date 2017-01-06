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
     * Ensures the truth of an {@code expression}.
     *
     * @param expression A boolean {@code expression}.
     * @throws IllegalArgumentException if {@code expression} is false.
     */
    public static void checkArgument(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Ensures the truth of an {@code expression}.
     *
     * @param expression A boolean {@code expression}.
     * @param message The exception message to use if the check fails.
     * @throws IllegalArgumentException if {@code expression} is false.
     * @throws NullPointerException if {@code message} is null
     */
    public static void checkArgument(boolean expression, String message) {
        Objects.requireNonNull(message);
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Ensures the truth of an {@code expression}.
     *
     * @param expression A boolean {@code expression}.
     * @param messageSupplier A supplier of the exception message to use if the check fails.
     * @throws IllegalArgumentException if {@code expression} is false.
     * @throws NullPointerException if {@code messageSupplier} is null
     */
    public static void checkArgument(boolean expression, Supplier<String> messageSupplier) {
        Objects.requireNonNull(messageSupplier);
        if (!expression) {
            throw new IllegalArgumentException(messageSupplier.get());
        }
    }

    /**
     * Ensures the collection is not empty.
     *
     * @param reference A collection.
     * @return {@code reference} if not empty.
     * @throws NullPointerException if {@code reference} is null.
     * @throws IllegalArgumentException if {@code reference} is empty.
     */
    public static <T> Collection<T> checkNotEmpty(Collection<T> reference) {
        Objects.requireNonNull(reference);
        if (reference.size() <= 0) {
            throw new IllegalArgumentException();
        }
        return reference;
    }

    /**
     * Ensures the collection is not empty.
     *
     * @param reference A collection.
     * @param message The exception message to use if the check fails.
     * @return {@code reference} if not empty.
     * @throws NullPointerException if {@code reference} is null or {@code message} is null.
     * @throws IllegalArgumentException if {@code reference} is empty.
     */
    public static <T> Collection<T> checkNotEmpty(Collection<T> reference, String message) {
        Objects.requireNonNull(message);
        Objects.requireNonNull(reference, message);
        if (reference.size() <= 0) {
            throw new IllegalArgumentException(message);
        }
        return reference;
    }

    /**
     * Ensures the collection is not empty.
     *
     * @param reference A collection.
     * @param messageSupplier A supplier of the exception message to use if the check fails.
     * @return {@code reference} if not empty.
     * @throws NullPointerException if {@code reference} is null or {@code messageSupplier} is null.
     * @throws IllegalArgumentException if {@code reference} is empty.
     */
    public static <T> Collection<T> checkNotEmpty(Collection<T> reference, Supplier<String> messageSupplier) {
        Objects.requireNonNull(messageSupplier);
        Objects.requireNonNull(reference, messageSupplier);
        if (reference.size() <= 0) {
            throw new IllegalArgumentException(messageSupplier.get());
        }
        return reference;
    }

    /**
     * Ensures an array is not empty.
     *
     * @param reference An array.
     * @return {@code reference} if not empty.
     * @throws NullPointerException if {@code reference} is null.
     * @throws IllegalArgumentException if {@code reference} is empty.
     */
    public static <T> T[] checkNotEmpty(T[] reference) {
        Objects.requireNonNull(reference);
        if (reference.length <= 0) {
            throw new IllegalArgumentException();
        }
        return reference;
    }

    /**
     * Ensures an array is not empty.
     *
     * @param reference An array.
     * @param message The exception message to use if the check fails.
     * @return {@code reference} if not empty.
     * @throws NullPointerException if {@code reference} is null or {@code message} is null.
     * @throws IllegalArgumentException if {@code reference} is empty.
     */
    public static <T> T[] checkNotEmpty(T[] reference, String message) {
        Objects.requireNonNull(message);
        Objects.requireNonNull(reference, message);
        if (reference.length <= 0) {
            throw new IllegalArgumentException(message);
        }
        return reference;
    }

    /**
     * Ensures an array is not empty.
     *
     * @param reference An array.
     * @param messageSupplier A supplier of the exception message to use if the check fails.
     * @return {@code reference} if not empty.
     * @throws NullPointerException if {@code reference} is null or {@code messageSupplier} is null.
     * @throws IllegalArgumentException if {@code reference} is empty.
     */
    public static <T> T[] checkNotEmpty(T[] reference, Supplier<String> messageSupplier) {
        Objects.requireNonNull(messageSupplier);
        Objects.requireNonNull(reference, messageSupplier);
        if (reference.length <= 0) {
            throw new IllegalArgumentException(messageSupplier.get());
        }
        return reference;
    }
}
