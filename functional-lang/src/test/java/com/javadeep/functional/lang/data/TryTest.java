package com.javadeep.functional.lang.data;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Test for {@linkplain Try}
 */
public class TryTest {

    @Test
    public void testOf_success() {
        Assert.assertEquals(Integer.valueOf(1), Try.of(() -> 3 / 2).get());
    }

    @Test(expected = RuntimeException.class)
    public void testOf_failure() {
        Try.of(() -> {
            throw new NullPointerException();
        }).get();
    }

    @Test
    public void testRun_success() {
        Assert.assertNull(Try.run(() -> String.valueOf(3 / 2)).get());
    }

    @Test(expected = RuntimeException.class)
    public void testRun_failure() {
        Try.run(() -> {
            throw new NullPointerException();
        }).get();
    }

    @Test
    public void testSuccess() {
        Assert.assertEquals(Integer.valueOf(1), Try.success(3 / 2).get());
    }

    @Test
    public void testFailure() {
        NullPointerException e = new NullPointerException();
        Assert.assertEquals(e, Try.failure(e).getCause());
    }

    @Test
    public void testOnSuccess() {
        List<Integer> integers = Stream.of(1, 2, 3).collect(Collectors.toList());
        Try.of(() -> 3 / 2).onSuccess(value -> integers.set(0, 5));
        Assert.assertEquals(Integer.valueOf(5), integers.get(0));
    }

    @Test
    public void testOnFailure() {
        List<Integer> integers = Stream.of(1, 2, 3).collect(Collectors.toList());
        Try.of(() -> {
            throw new NullPointerException();
        }).onFailure(value -> integers.set(0, 5));
        Assert.assertEquals(Integer.valueOf(5), integers.get(0));
    }

    @Test
    public void testOrElse() {
        Assert.assertEquals(Integer.valueOf(3),
                Try.<Integer>of(() -> {
                    throw new NullPointerException();
                }).orElse(3));
    }

    @Test
    public void testOrElseGet() {
        Assert.assertEquals(Integer.valueOf(3),
                Try.<Integer>of(() -> {
                    throw new NullPointerException();
                }).orElseGet(() -> 3));
    }

    @Test
    public void testOrElseMap() {
        Assert.assertEquals(Integer.valueOf(3),
                Try.<Integer>of(() -> {
                    throw new NullPointerException();
                }).orElseMap(e -> 3));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testOrElseThrow() {
        Try.of(() -> {
            throw new NullPointerException();
        }).orElseThrow(UnsupportedOperationException::new);
    }

    @Test
    public void testMap() {
        Assert.assertEquals(Try.of(() -> "value:1"),
                Try.of(() -> 3 / 2)
                        .map(e -> String.format("value:%d", e)));
    }

    @Test(expected = RuntimeException.class)
    public void testRecover() {
        Try.of(() -> {
            throw new NullPointerException();
        }).recover(e -> {
            throw new UnsupportedOperationException(e);
        }).get();
    }

    @Test
    public void testRecoverWith() {
        Assert.assertEquals(Try.of(() -> 1),
                Try.of(() -> {
                    throw new NullPointerException();
                }).recoverWith(e -> Try.of(() -> 3 / 2)));
    }

    @Test
    public void testFold_success() {
        Assert.assertEquals("fold success value: 1",
                Try.of(() -> 3 /2)
                        .fold(e -> String.format("fold success value: %d", e),
                                e -> String.format("fold exception: %s",
                                        e.getMessage())));
    }

    @Test
    public void testFold_failure() {
        Assert.assertEquals("fold exception: null",
                Try.<Integer>of(() -> {
                    throw new NullPointerException("null");
                }).fold(e -> String.format("fold success value: %d", e),
                        e -> String.format("fold exception: %s", e.getMessage())));
    }

    @Test
    public void testPeek() {
        List<Integer> integers = Stream.of(1, 2, 3).collect(Collectors.toList());
        Try.of(() -> 10 / 2).peek(value -> integers.set(0, value.get()));
        Assert.assertEquals(Integer.valueOf(5), integers.get(0));
    }

    @Test
    public void testToEither_success() {
        Assert.assertEquals(Either.right(1), Try.of(() -> 3 / 2).toEither());
    }

    @Test
    public void testToEither_failure() {
        NullPointerException e = new NullPointerException("null");
        Assert.assertEquals(Either.left(e),
                Try.of(() -> {
                    throw e;
                }).toEither());
    }
}
