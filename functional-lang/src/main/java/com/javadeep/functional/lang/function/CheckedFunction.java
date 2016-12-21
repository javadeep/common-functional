package com.javadeep.functional.lang.function;

/**
 * A {@linkplain java.util.function.Function} which may throw.
 *
 * @param <T> the type of the input to the function
 * @param <R> the result type of the function
 * @author baojie
 * @since 1.0.0
 */
public interface CheckedFunction<T, R> {

    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     * @throws Throwable if an error occurs
     */
    R apply(T t) throws Throwable;
}
