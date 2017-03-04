package com.javadeep.functional.lang.control.validator.bo;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for {@linkplain ValidationError}
 */
public class ValidationErrorTest {

    @Test
    public void testValidationError_1() {
        ValidationError error = ValidationError.of("error")
                .field("field")
                .errorCode(123)
                .invalidValue(null);
        Assert.assertEquals("error", error.getErrorMsg());
        Assert.assertEquals(123, error.getErrorCode());
        Assert.assertEquals("field", error.getField());
        Assert.assertNull(error.getInvalidValue());

    }

    @Test
    public void testValidationError_2() {
        ValidationError error = ValidationError.of("errors:%d", 1)
                .field("fields")
                .errorCode(1234)
                .invalidValue(3);
        Assert.assertEquals("errors:%d", error.getErrorMsg());
        Assert.assertArrayEquals(new Object[] {1}, error.getParams());
        Assert.assertEquals(1234, error.getErrorCode());
        Assert.assertEquals("fields", error.getField());
        Assert.assertEquals(3, error.getInvalidValue());

    }
}
