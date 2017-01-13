package com.javadeep.functional.lang.control.validator;

import com.javadeep.functional.lang.control.validator.bo.ValidationError;
import com.javadeep.functional.lang.control.validator.bo.ValidationResult;
import com.javadeep.functional.lang.data.Duration;
import com.javadeep.functional.lang.data.Try;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Test for {@linkplain FunctionalValidator}
 */
public class FunctionalValidatorTest {

    @Test
    public void testOn_success() {
        Try<ValidationResult> result = FunctionalValidator.checkFrom(123)
                .failOver()
                .on(Objects::nonNull, ValidationError.of("error"))
                .on(t -> t > 0, "t > 0")
                .on(t -> t > 0, Stream.of(ValidationError.of("error")))
                .on(t -> Stream.empty())
                .doValidate()
                .getResult();
        Assert.assertEquals(true, result.get().isSuccess());
    }

    @Test
    public void testOn_failure() {
        Try<ValidationResult> result = FunctionalValidator.<Duration>checkFrom(null)
                .failFast()
                .on(Objects::nonNull, "duration is null")
                .on(d -> d.toNanos() > 0, "error")
                .doValidate()
                .getResult();
        Assert.assertEquals(false, result.get().isSuccess());
        Assert.assertEquals(1, result.get().getErrors().size());
    }

    @Test(expected = RuntimeException.class)
    public void testOn_exception() {
        FunctionalValidator.<Duration>checkFrom(null)
                .failOver()
                .on(Objects::nonNull, "duration is null")
                .on(d -> d.toNanos() > 0, "error")
                .doValidate()
                .getResult()
                .get();

    }

    @Test
    public void testOnIf_fold_success() {
        String ret = FunctionalValidator.checkFrom(Duration.of(3, TimeUnit.DAYS))
                .failFast()
                .on(Objects::nonNull, "duration is null")
                .on(d -> d.toNanos() > 0, "error")
                .onIf(d -> d.toNanos() < 0, "error", d -> false)
                .doValidate()
                .fold(t -> "success", r -> "failure", e -> "exception");
        Assert.assertEquals("success", ret);
    }

    @Test
    public void testOnIf_fold_failure() {
        String ret = FunctionalValidator.checkFrom(Duration.of(3, TimeUnit.DAYS))
                .failFast()
                .on(Objects::nonNull, "duration is null")
                .on(d -> d.toNanos() > 0, "error")
                .onIf(d -> d.toNanos() < 0, "error", d -> true)
                .doValidate()
                .fold(t -> "success", r -> "failure", e -> "exception");
        Assert.assertEquals("failure", ret);
    }

    @Test
    public void testOnIf_fold_exception() {
        String ret = FunctionalValidator.<Duration>checkFrom(null)
            .failOver()
            .on(Objects::nonNull, "duration is null")
            .on(d -> d.toNanos() > 0, "error")
            .onIf(d -> d.toNanos() < 0, "error", d -> true)
            .doValidate()
            .fold(t -> "success", r -> "failure", e -> "exception");
        Assert.assertEquals("exception", ret);
    }

    @Test(expected = RuntimeException.class)
    public void testOnIf_foldIgnoreException() {
        FunctionalValidator.<Duration>checkFrom(null)
                .failOver()
                .on(Objects::nonNull, "duration is null")
                .on(d -> d.toNanos() > 0, "error")
                .onIf(d -> d.toNanos() < 0, "error", d -> true)
                .doValidate()
                .foldIgnoreThrowable(t -> "success", r -> "failure");
    }

    @Test
    public void testOnIf_onResult() {
        List<Long> longs = Stream.of(0L, 0L, 0L).collect(Collectors.toList());
        FunctionalValidator.checkFrom(Duration.of(3, TimeUnit.DAYS))
                .failFast()
                .on(Objects::nonNull, "duration is null")
                .on(d -> d.toNanos() > 0, "error")
                .onIf(d -> d.toNanos() < 0, "error", d -> false)
                .doValidate()
                .onResult(result -> longs.set(0, result.isSuccess() ? 1L : 0L));
        Assert.assertEquals(Long.valueOf(1), longs.get(0));
    }

    @Test
    public void testOnIf_onSuccess() {
        List<Long> longs = Stream.of(0L, 0L, 0L).collect(Collectors.toList());
        FunctionalValidator.checkFrom(Duration.of(3, TimeUnit.DAYS))
                .failFast()
                .on(Objects::nonNull, "duration is null")
                .on(d -> d.toNanos() > 0, "error")
                .onIf(d -> d.toNanos() < 0, "error", d -> false)
                .doValidate()
                .onSuccess(d -> longs.set(0, d.toNanos()));
        Assert.assertTrue(longs.get(0) > 0);
    }

    @Test
    public void testOnIf_onFailure() {
        List<Long> longs = Stream.of(0L, 0L, 0L).collect(Collectors.toList());
        FunctionalValidator.checkFrom(Duration.of(3, TimeUnit.DAYS))
                .failFast()
                .on(Objects::nonNull, "duration is null")
                .on(d -> d.toNanos() > 0, "error")
                .onIf(d -> d.toNanos() < 0, "error", d -> true)
                .doValidate()
                .onFailure(result -> longs.set(0, result.isSuccess() ? 2L : 1L));
        Assert.assertEquals(Long.valueOf(1), longs.get(0));
    }

    @Test
    public void testOnIf_onException() {
        List<Long> longs = Stream.of(0L, 0L, 0L).collect(Collectors.toList());
        FunctionalValidator.<Duration>checkFrom(null)
                .failOver()
                .on(Objects::nonNull, "duration is null")
                .on(d -> d.toNanos() > 0, "error")
                .onIf(d -> d.toNanos() < 0, "error", d -> true)
                .doValidate()
                .onThrowable(t -> longs.set(0, t instanceof NullPointerException ? 1L : 0L));
        Assert.assertEquals(Long.valueOf(1), longs.get(0));
    }
}
