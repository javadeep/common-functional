package com.javadeep.functional.lang.function;

import java.util.function.Function;

/**
 * A {@linkplain Function} which may throw.
 *
 * @param <T> the type of the input to the function
 * @param <R> the type of the result of the function
 *
 * @author baojie
 * @version 1.0.0
 */
@FunctionalInterface
public interface CheckedFunction<T, R> {

    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     * @throws Throwable Throwable if an error occurs
     */
    R apply(T t) throws Throwable;


    /**
     * Transform {@code CheckedFunction} to {@code Function}.
     *
     * @return The result function of {@code Function}.
     */
    default Function<T, R> toFunction() {
        return t -> {
            try {
                return apply(t);
            } catch (Throwable th) {
                throw new RuntimeException(th);
            }
        };
    }
}
