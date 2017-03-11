package com.javadeep.functional.lang.control.base;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Test for {@linkplain Optionals}
 */
public class OptionalsTest {

    @Test
    public void testConsume() {

        List<Integer> lists = new ArrayList<>(1);
        Optionals.consume(Optional.of(5), lists::add, () -> lists.add(0));
        Assert.assertEquals(Integer.valueOf(5), lists.get(0));
    }
}
