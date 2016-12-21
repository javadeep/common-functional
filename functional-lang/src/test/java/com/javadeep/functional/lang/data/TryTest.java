package com.javadeep.functional.lang.data;

import org.junit.Assert;
import org.junit.Test;


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
}
