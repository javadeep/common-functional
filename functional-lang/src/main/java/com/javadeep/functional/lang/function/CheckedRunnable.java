package com.javadeep.functional.lang.function;

/**
 * A {@linkplain java.lang.Runnable} which may throw.
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
