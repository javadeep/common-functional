package com.javadeep.functional.lang.function;

import java.util.function.Function;

/**
 * Transformations on functions.
 *
 * @author baojie
 * @since 1.0.0
 */
public final class Functions {

    /**
     * Transform {@code CheckedFunction} to {@code Function}.
     *
     * @param checkedFunction The {@code CheckedFunction}
     * @param <T> the type of the input to the {@code CheckedFunction}.
     * @param <R> the type of the result of the {@code CheckedFunction}.
     * @return The result function of {@code Function}.
     */
    public static <T, R> Function<T, R> uncheckedFunction(CheckedFunction<T, R> checkedFunction) {
        return checkedFunction.toFunction();
    }
}
