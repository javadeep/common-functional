package com.javadeep.functional.lang.data;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for {@linkplain Duration}
 */
public class DurationTest {

    @Test
    public void testToNanos() {
        Assert.assertEquals(0, Duration.NONE.toNanos());
    }
}
