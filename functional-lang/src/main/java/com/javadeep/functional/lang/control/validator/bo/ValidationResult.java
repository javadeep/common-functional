package com.javadeep.functional.lang.control.validator.bo;

import java.util.List;

/**
 * The result of validation.
 *
 * @author baojie
 * @since 1.0.0
 */
public final class ValidationResult {

    private boolean isSuccess = true;

    private List<ValidationError> errors;

    private int timeElapsed;
}
