package com.javadeep.functional.lang.control.validator.bo;

import org.junit.Assert;
import org.junit.Test;

import java.util.stream.Stream;

/**
 * Test for {@linkplain ValidationResult}
 */
public class ValidationResultTest {

    @Test
    public void testAddError_addError_success() {
        ValidationResult result = ValidationResult.build()
                .addError(ValidationError.builder("error").build())
                .success()
                .timeElapsed(10);
        Assert.assertEquals(1, result.getErrors().size());
        Assert.assertEquals(true, result.isSuccess());
        Assert.assertEquals(10, result.getTimeElapsed());
    }

    @Test
    public void testAddError_addErrors_array_failure() {
        ValidationResult result = ValidationResult.build()
                .addErrors(ValidationError.builder("failure").build())
                .failure()
                .timeElapsed(15);
        Assert.assertEquals(1, result.getErrors().size());
        Assert.assertEquals(false, result.isSuccess());
        Assert.assertEquals(15, result.getTimeElapsed());
    }

    @Test
    public void testAddError_addErrors_collection_success() {
        ValidationResult result = ValidationResult.build()
                .addErrors(Stream.of(ValidationError.builder("failure").build()))
                .timeElapsed(20);
        Assert.assertEquals(1, result.getErrors().size());
        Assert.assertEquals(true, result.isSuccess());
        Assert.assertEquals(20, result.getTimeElapsed());
    }
}
