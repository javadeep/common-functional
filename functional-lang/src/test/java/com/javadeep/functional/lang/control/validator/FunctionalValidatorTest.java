package com.javadeep.functional.lang.control.validator;

import com.javadeep.functional.lang.control.validator.bo.ValidationError;
import com.javadeep.functional.lang.control.validator.bo.ValidationResult;
import com.javadeep.functional.lang.data.Duration;
import com.javadeep.functional.lang.data.Try;
import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;
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
}
