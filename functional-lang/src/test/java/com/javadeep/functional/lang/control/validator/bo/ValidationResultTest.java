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
                .addError(ValidationError.of("error"))
                .timeElapsed(10);
        Assert.assertEquals(1, result.getErrors().size());
        Assert.assertEquals(false, result.isSuccess());
        Assert.assertEquals(10, result.getTimeElapsed());
    }

    @Test
    public void testAddError_addErrors_array_failure() {
        ValidationResult result = ValidationResult.build()
                .addErrors(ValidationError.of("failure"))
                .timeElapsed(15);
        Assert.assertEquals(1, result.getErrors().size());
        Assert.assertEquals(false, result.isSuccess());
        Assert.assertEquals(15, result.getTimeElapsed());
    }

    @Test
    public void testAddError_addErrors_collection_success() {
        ValidationResult result = ValidationResult.build()
                .addErrors(Stream.of(ValidationError.of("failure")))
                .timeElapsed(20);
        Assert.assertEquals(1, result.getErrors().size());
        Assert.assertEquals(false, result.isSuccess());
        Assert.assertEquals(20, result.getTimeElapsed());
    }

    @Test
    public void testAddGlobalError_codeAndMessage() {
        ValidationResult result = ValidationResult.build()
                .addGlobalError(12, "globalError")
                .timeElapsed(20);
        Assert.assertEquals(0, result.getErrors().size());
        Assert.assertEquals(false, result.isSuccess());
        Assert.assertEquals(20, result.getTimeElapsed());
        Assert.assertEquals(12, result.getGlobalErrorCode());
        Assert.assertEquals("globalError", result.getGlobalErrorMessage());
    }

    @Test
    public void testAddGlobalError_codeAndMessage2() {
        ValidationResult result = ValidationResult.build()
                .addGlobalError(13, "globalError2")
                .timeElapsed(20);
        Assert.assertEquals(0, result.getErrors().size());
        Assert.assertEquals(false, result.isSuccess());
        Assert.assertEquals(20, result.getTimeElapsed());
        Assert.assertEquals(13, result.getGlobalErrorCode());
        Assert.assertEquals("globalError2", result.getGlobalErrorMessage());
    }

    @Test
    public void testAddGlobalError_message() {
        ValidationResult result = ValidationResult.build()
                .addGlobalError("globalError")
                .timeElapsed(20);
        Assert.assertEquals(0, result.getErrors().size());
        Assert.assertEquals(false, result.isSuccess());
        Assert.assertEquals(20, result.getTimeElapsed());
        Assert.assertEquals(0, result.getGlobalErrorCode());
        Assert.assertEquals("globalError", result.getGlobalErrorMessage());
    }

    @Test
    public void testAddGlobalError_message2() {
        ValidationResult result = ValidationResult.build()
                .addGlobalError("globalError2")
                .timeElapsed(20);
        Assert.assertEquals(0, result.getErrors().size());
        Assert.assertEquals(false, result.isSuccess());
        Assert.assertEquals(20, result.getTimeElapsed());
        Assert.assertEquals(0, result.getGlobalErrorCode());
        Assert.assertEquals("globalError2", result.getGlobalErrorMessage());
    }
}
