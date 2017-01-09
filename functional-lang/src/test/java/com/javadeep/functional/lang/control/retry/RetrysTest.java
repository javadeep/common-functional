package com.javadeep.functional.lang.control.retry;


import com.javadeep.functional.lang.data.Try;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Test for {@linkplain Retrys}
 */
public class RetrysTest {

    @Test(expected = Exception.class)
    public void testRun() {

        Retrys.with(RetryPolicy.never()).run(() -> {
            throw new NullPointerException();
        }).get();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGet() {

        List<Integer> results = Stream.of(0, 1, 2).collect(Collectors.toList());
        Assert.assertEquals(Try.success(3),
                Retrys.with(RetryPolicy.initWithMaxRetries(5))
                        .get(() -> {
                            if (results.get(0) < 3) {
                                results.set(0, results.get(0) + 1);
                                throw new NullPointerException();
                            }
                            return results.get(0);
                        }));
    }
}
