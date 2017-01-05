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
    public void testCheckArgument_withoutMessage_false() {
        Preconditions.checkArgument(!Collections.emptyList().isEmpty());
    }

    @Test
    public void testCheckArgument_withoutMessage_true() {
        Preconditions.checkArgument(true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckArgument_withMessageProvider_false() {
        Preconditions.checkArgument(false, () -> "message1");
    }

    @Test
    public void testCheckArgument_withMessageProvider_true() {
        Preconditions.checkArgument(true, () -> "message2");
    }

    @Test(expected = NullPointerException.class)
    public void testCheckNotEmpty_array_withoutMessage() {
        Preconditions.checkNotEmpty((Integer[]) null);
    }

    @Test(expected = NullPointerException.class)
    public void testCheckNotEmpty_array_withMessage() {
        Preconditions.checkNotEmpty((Integer[]) null, "msg");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckNotEmpty_array_withMessageProvider() {
        Preconditions.checkNotEmpty(Stream.of().toArray(), () -> "message3");
    }

    @Test(expected = NullPointerException.class)
    public void testCheckNotEmpty_collection_withoutMessage() {
        Preconditions.checkNotEmpty((List<Integer>)null);
    }

    @Test(expected = NullPointerException.class)
    public void testCheckNotEmpty_collection_withMessage() {
        Preconditions.checkNotEmpty((List<Integer>)null, "msg2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckNotEmpty_collection_withMessageProvider() {
        Preconditions.checkNotEmpty(Collections.emptyList(), () -> "message4");
    }
}
