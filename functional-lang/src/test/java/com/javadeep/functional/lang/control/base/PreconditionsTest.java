package com.javadeep.functional.lang.control.base;

import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * Test for {@linkplain Preconditions}
 */
public class PreconditionsTest {

    @Test(expected = IllegalArgumentException.class)
    public void testCheckArgument_withoutMessage() {
        Preconditions.checkArgument(false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckArgument_withMessageProvider() {
        Preconditions.checkArgument(false, () -> "message");
    }

    @Test(expected = NullPointerException.class)
    public void testCheckNotEmpty_array_withoutMessage() {
        Preconditions.checkNotEmpty((Integer[]) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckNotEmpty_array_withMessageProvider() {
        Preconditions.checkNotEmpty(Stream.of().toArray(), () -> "message");
    }

    @Test(expected = NullPointerException.class)
    public void testCheckNotEmpty_collection_withoutMessage() {
        Preconditions.checkNotEmpty((List<Integer>)null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckNotEmpty_collection_withMessageProvider() {
        Preconditions.checkNotEmpty(Collections.emptyList(), () -> "message");
    }
}
