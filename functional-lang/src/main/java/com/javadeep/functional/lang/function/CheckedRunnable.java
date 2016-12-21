package com.javadeep.functional.lang.function;

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
}
