package com.javadeep.functional.lang.data;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Test for {@linkplain Either}
 */
public class EitherTest {

    @Test
    public void testOrElse_left() {
        Assert.assertEquals(Integer.valueOf(5),
                Either.<Throwable, Integer>left(new NullPointerException()).orElse(5));
    }

    @Test
    public void testOrElse_right() {
        Assert.assertEquals(Integer.valueOf(5),
                Either.right(5).orElse(3));
    }

    @Test
    public void testOrElseGet() {
        Assert.assertEquals(Integer.valueOf(5),
                Either.<Throwable, Integer>left(new NullPointerException()).orElseGet(() -> 5));
    }

    @Test
    public void testOrElseMap() {
        Assert.assertEquals(Integer.valueOf(5),
                Either.<Throwable, Integer>left(new NullPointerException()).orElseMap(e -> 5));
    }

    @Test
    public void testOrElseRun() {
        List<Integer> integers = Stream.of(1, 2, 3).collect(Collectors.toList());
        Either.left(123).orElseRun(e -> integers.set(0, e));
        Assert.assertEquals(Integer.valueOf(123), integers.get(0));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testOrElseThrow() {
        Either.left(new NullPointerException("null")).orElseThrow(UnsupportedOperationException::new);
    }

    @Test
    public void testMap() {
        Assert.assertEquals(Either.right(5),
                Either.<Exception, String>right("abc").map(e -> new Exception(), s -> 5));
    }

    @Test
    public void testMapRight() {
        Assert.assertEquals(Either.right(5),
                Either.right("abc").mapRight(s -> 5));
    }

    @Test
    public void testMapLeft() {
        Assert.assertEquals(Either.left(5),
                Either.left("abc").mapLeft(s -> 5));
    }

    @Test
    public void testFold() {
        Assert.assertEquals(Integer.valueOf(5),
                Either.<Exception, String>right("abc").fold(e -> 1, s -> 5));
    }

    @Test
    public void testPeek() {
        List<Integer> integers = Stream.of(1, 2, 3).collect(Collectors.toList());
        Either.right(5).peek(either -> integers.set(0, either.getRight()));
        Assert.assertEquals(Integer.valueOf(5), integers.get(0));
    }
}
