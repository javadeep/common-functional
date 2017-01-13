package com.javadeep.functional.lang.control.retry;

import com.javadeep.functional.lang.data.Try;
import com.javadeep.functional.lang.function.CheckedRunnable;

import java.util.Objects;
import java.util.concurrent.Callable;

/**
 * Simple, sophisticated retry handling.
 *
 * @param <T> Value type of result.
 *
 * @author baojie02
 * @since 1.0.0
 */
public final class Retrys<T> {

    private final RetryPolicy<T> retryPolicy;

    /**
     * Constructs a {@code Retrys}.
     *
     * @param retryPolicy the policy of retry.
     */
    private Retrys(RetryPolicy<T> retryPolicy) {
        this.retryPolicy = retryPolicy;
    }

    /**
     * Creates and returns a new {@code Retrys} instance that will perform executions and retries according to
     * the {@code retryPolicy}.
     *
     * @param <T> the result type.
     * @param retryPolicy the policy of retry.
     * @return a new {@code Retrys} instance.
     * @throws NullPointerException if {@code retryPolicy} is null.
     */
    @SuppressWarnings("unchecked")
    public static <T> Retrys with(RetryPolicy<T> retryPolicy) {
        return new Retrys(Objects.requireNonNull(retryPolicy, "retryPolicy is null"));
    }

    /**
     * Executes the {@code runnable} until successful or until the configured {@link RetryPolicy} is exceeded.
     *
     * @param runnable the {@code runnable} to execute.
     * @return the result for executes the {@code runnable}.
     * @throws NullPointerException if {@code runnable} is null.
     */
    public final Try<T> run(CheckedRunnable runnable) {
        Objects.requireNonNull(runnable, "runnable is null");
        return call(runnable.toCallable());
    }

    /**
     * Executes the {@code callable} until successful or until the configured {@link RetryPolicy} is exceeded.
     *
     * @param callable the {@code callable} to execute.
     * @return the result for executes the {@code callable}.
     * @throws NullPointerException if {@code callable} is null.
     */
    public final Try<T> get(Callable<? extends T> callable) {
        return call(Objects.requireNonNull(callable, "callable is null"));
    }

    private Try<T> call(Callable<? extends T> callable) {
        int retryCount = 0;
        Try<T> result = Try.of(callable::call);
        while (retryPolicy.canRetryFor(result, retryCount)) {

            try {
                retryPolicy.getDelay().sleep();
            } catch (InterruptedException e) {
                return Try.failure(e);
            }

            result = Try.of(callable::call);

            ++retryCount;
        }
        return result;
    }
}
