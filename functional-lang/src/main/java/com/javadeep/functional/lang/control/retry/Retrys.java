package com.javadeep.functional.lang.control.retry;

import java.util.Objects;

/**
 * Simple, sophisticated retry handling.
 *
 * @author baojie02
 * @since 1.0.0
 */
public final class Retrys<T> {

    private final RetryPolicy<T> retryPolicy;

    private Retrys(RetryPolicy<T> retryPolicy) {
        this.retryPolicy = retryPolicy;
    }

    public static <T> Retrys with(RetryPolicy<T> retryPolicy) {
        return new Retrys(Objects.requireNonNull(retryPolicy, "retryPolicy is null"));
    }
}
