package com.javadeep.functional.lang.control.retry;

import com.javadeep.functional.lang.data.Duration;
import com.javadeep.functional.lang.data.Try;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Test for {@linkplain RetryPolicy}
 */
public class RetryPolicyTest {

    @Test
    public void testNever() {
        Assert.assertEquals(false, RetryPolicy.never().canRetryFor(Try.of(() -> 3 / 2), 0));
    }

    @Test
    public void testRetryIf() {
        Assert.assertEquals(true,
                RetryPolicy.<Integer>initWithMaxRetries(1)
                        .retryIf(a -> a > 3)
                        .canRetryFor(Try.of(() -> 4), 0));
    }

    @Test
    public void testRetryOn_array() {
        Assert.assertEquals(true,
                RetryPolicy.initWithMaxRetries(2)
                        .retryOn(Exception.class)
                        .canRetryFor(Try.of(() -> {
                            throw new NullPointerException();
                        }), 1));
    }

    @Test
    public void testRetryOn_collection() {
        Assert.assertEquals(true,
                RetryPolicy.initWithMaxRetries(2)
                        .retryOn(Stream.of(Exception.class).collect(Collectors.toList()))
                        .canRetryFor(Try.of(() -> {
                            throw new NullPointerException();
                        }), 1));
    }

    @Test
    public void testRetryOn_predicate() {
        Assert.assertEquals(true,
                RetryPolicy.<Integer>initWithMaxRetries(1)
                        .retryOn(e -> e instanceof NullPointerException)
                        .canRetryFor(Try.of(() -> {
                            throw new NullPointerException();
                        }), 0));
    }

    @Test
    public void testRetryWhen_true() {
        Assert.assertEquals(true,
                RetryPolicy.<Integer>initWithMaxRetries(1)
                        .retryWhen(4)
                        .canRetryFor(Try.of(() -> 4), 0));
    }

    @Test
    public void testRetryWhen_false() {
        Assert.assertEquals(false,
                RetryPolicy.<Integer>initWithMaxRetries(1)
                        .retryWhen(3)
                        .canRetryFor(Try.of(() -> 4), 0));
    }

    @Test
    public void testWithDelay_ms() {
        Assert.assertEquals(Duration.of(10, TimeUnit.SECONDS),
                RetryPolicy.<Integer>initWithMaxRetries(1)
                        .withDelay(10000, TimeUnit.MILLISECONDS)
                        .getDelay());
    }

    @Test
    public void testWithDelay_s() {
        Assert.assertEquals(Duration.of(10, TimeUnit.SECONDS),
                RetryPolicy.<Integer>initWithMaxRetries(1)
                        .withDelay(10, TimeUnit.SECONDS)
                        .getDelay());
    }
}
