package com.javadeep.functional.lang.function;

import java.util.concurrent.Callable;

/**
 * A {@linkplain java.lang.Runnable} which may throw.
 *
 * @author baojie
 * @since 1.0.0
 */
@FunctionalInterface
public interface CheckedRunnable {

    /**
     * Performs side-effects.
     *
     * @throws Throwable Throwable if an error occurs
     */
    void run() throws Throwable;

    /**
     * Transform {@code CheckedRunnable} to {@code Callable}.
     *
     * @param <T> the result type.
     * @return The result function of {@code Callable}.
     */
    default <T> Callable<T> toCallable() {
        return () -> {
            try {
                run();
            } catch (Throwable e) {
                throw new Exception(e);
            }
            return null;
        };
    }
}
