package com.javadeep.functional.lang.control.time;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

/**
 * Test for {@linkplain LocalDates}
 */
public class LocalDatesTest {

    @Test
    public void testToEpochSecond() {
        int start = LocalDates.toStartEpochSecond(LocalDate.of(2017, 1, 1));
        int end = LocalDates.toEndEpochSecond(LocalDate.of(2017, 1, 2));
        Assert.assertEquals(24*60*60*2-1, end - start);
    }
}
