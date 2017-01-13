package com.javadeep.functional.lang.control.retry;

import com.javadeep.functional.lang.control.base.Preconditions;
import com.javadeep.functional.lang.data.Duration;
import com.javadeep.functional.lang.data.Try;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * A policy that defines when retries should be performed.
 *
 * @param <T> Value type of result.
 *
 * @author baojie
 * @since 1.0.0
 */
public final class RetryPolicy<T> {

    private final int maxRetries;
    private Duration delay = Duration.NONE;
    private List<Predicate<Try<T>>> retryConditions = new LinkedList<>();

    /**
     * Constructs a {@code RetryPolicy}.
     *
     * @param maxRetries max number of retries.
     */
    private RetryPolicy(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    /**
     * Init the retry policy of never retry.
     *
     * @param <T> the result type.
     * @return the instance of <code>RetryPolicy</code>
     */
    @SuppressWarnings("unchecked")
    public static <T> RetryPolicy<T> never() {
        return initWithMaxRetries(0);
    }

    /**
     * Init the retry policy by set the max number of retries to perform. {@code -1} indicates to retry forever.
     *
     * @param <T> the result type.
     * @param maxRetries the max number of retry times
     * @return the instance of <code>RetryPolicy</code>
     * @throws IllegalArgumentException if {@code maxRetries} less than {@code -1}
     */
    @SuppressWarnings("unchecked")
    public static <T> RetryPolicy<T> initWithMaxRetries(int maxRetries) {
        Preconditions.checkArgument(maxRetries >= -1, "maxRetries must be greater than or equal to -1");
        return new RetryPolicy(maxRetries);
    }

    /**
     * Specifies that a retry should occur if the {@code resultPredicate} matches the result and the retry policy is not
     * exceeded.
     *
     * @param resultPredicate The <code>Predicate</code> of result.
     * @return The instance of <code>RetryPolicy</code>.
     * @throws NullPointerException if {@code resultPredicate} is null.
     */
    public final RetryPolicy<T> retryIf(Predicate<? super T> resultPredicate) {
        Objects.requireNonNull(resultPredicate, "resultPredicate is null");
        retryConditions.add(t -> t.fold(resultPredicate::test, failure -> true));
        return this;
    }

    /**
     * Specifies the failures to retry on. Any failure that is assignable from the {@code failures} will be retried.
     *
     * @param failures The array of failures.
     * @return The instance of <code>RetryPolicy</code>.
     * @throws NullPointerException if {@code failures} is null.
     * @throws IllegalArgumentException if {@code failures} is empty.
     */
    @SafeVarargs
    public final RetryPolicy<T> retryOn(Class<? extends Throwable>... failures) {
        Preconditions.checkNotEmpty(failures, "failures is empty");
        return retryOn(Stream.of(failures));
    }

    /**
     * Specifies the failures to retry on. Any failure that is assignable from the {@code failures} will be retried.
     *
     * @param failures The collection of failures.
     * @return The instance of <code>RetryPolicy</code>.
     * @throws NullPointerException if {@code failures} is null.
     * @throws IllegalArgumentException if {@code failures} is empty.
     */
    public final RetryPolicy<T> retryOn(Collection<Class<? extends Throwable>> failures) {
        Preconditions.checkNotEmpty(failures, "failures is empty");
        return retryOn(failures.stream());
    }

    /**
     * Specifies the failures to retry on. Any failure that is assignable from the {@code failures} will be retried.
     *
     * @param failures The stream of failures.
     * @return The instance of <code>RetryPolicy</code>.
     * @throws NullPointerException if {@code failures} is null.
     */
    public final RetryPolicy<T> retryOn(Stream<Class<? extends Throwable>> failures) {
        Objects.requireNonNull(failures, "failures is null");
        retryConditions.add(t -> t.fold(v -> false,
                e -> failures.anyMatch((f -> f.isAssignableFrom(e.getClass())))));
        return this;
    }

    /**
     * Returns a predicate that evaluates the {@code failurePredicate} against a failure.
     *
     * @param failurePredicate The <code>Predicate</code> of Throwable.
     * @return The instance of <code>RetryPolicy</code>.
     * @throws NullPointerException if {@code failurePredicate} is null.
     */
    @SuppressWarnings("unchecked")
    public final RetryPolicy<T> retryOn(Predicate<? super Throwable> failurePredicate) {
        Objects.requireNonNull(failurePredicate, "failurePredicate is null");
        retryConditions.add(t -> t.fold(v -> false, ((Predicate<Throwable>) failurePredicate)::test));
        return this;
    }

    /**
     * Specifies that a retry should occur if the execution result matches the {@code result} and
     * the retry policy is not exceeded.
     *
     * @param result The result need matches.
     * @return The instance of <code>RetryPolicy</code>.
     */
    public final RetryPolicy<T> retryWhen(T result) {
        retryConditions.add(t -> t.fold(v -> Objects.equals(v, result), e -> true));
        return this;
    }

    /**
     *
     * Sets the {@code delay} between retries.
     *
     * @param delay length of delay.
     * @param timeUnit timeUnit.
     * @return The instance of <code>RetryPolicy</code>.
     * @throws NullPointerException if {@code timeUnit} is null.
     * @throws IllegalArgumentException if {@code timeUnit} is less than 0.
     */
    public final RetryPolicy<T> withDelay(long delay, TimeUnit timeUnit) {
        Objects.requireNonNull(timeUnit, "timeUnit is null");
        Preconditions.checkArgument(delay > 0, "delay must be greater than 0");
        this.delay = Duration.of(delay, timeUnit);
        return this;
    }

    /**
     * Returns whether an execution result can be retried given the configured retry conditions.
     *
     * @param result The execution result.
     * @param retryCount the times has been retried.
     * @return true if result can be retried, false otherwise.
     * @throws NullPointerException if {@code result} is null.
     */
    public final boolean canRetryFor(Try<T> result, int retryCount) {
        Objects.requireNonNull(result, "result is null");

        if (maxRetries != -1 && retryCount >= maxRetries) {
            return false;
        }

        if (retryConditions.size() == 0) {
            return result.isFailure();
        }
        return retryConditions.stream().anyMatch(p -> p.test(result));
    }

    public Duration getDelay() {
        return delay;
    }
}
