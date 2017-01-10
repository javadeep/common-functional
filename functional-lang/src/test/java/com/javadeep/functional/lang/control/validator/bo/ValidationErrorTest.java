package com.javadeep.functional.lang.control.validator.bo;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for {@linkplain ValidationError}
 */
public class ValidationErrorTest {

    @Test
    public void testValidationError() {
        ValidationError error = ValidationError.builder("error")
                .field("field")
                .errorCode(123)
                .invalidValue(null)
                .build();
        Assert.assertEquals("error", error.getErrorMsg());
        Assert.assertEquals(123, error.getErrorCode());
        Assert.assertEquals("field", error.getField());
        Assert.assertNull(error.getInvalidValue());

    }
}
