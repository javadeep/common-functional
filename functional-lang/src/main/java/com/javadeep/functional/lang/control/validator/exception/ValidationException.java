package com.javadeep.functional.lang.control.validator.exception;

import com.javadeep.functional.lang.control.validator.bo.ValidationResult;

import java.util.Objects;

/**
 * Validation Exception
 *
 * @author baojie
 * @since 1.0.0
 */
public class ValidationException extends RuntimeException {

    private final ValidationResult result;

    /**
     * Construct the {@code ValidationExcepiton} instance.
     *
     * @param result The {@code ValidationResult}
     * @throws NullPointerException if {@code result} is null
     */
    public ValidationException(ValidationResult result) {
        super(Objects.requireNonNull(result, "result is null").getGlobalErrorMessage());
        this.result = result;
    }

    public ValidationResult getResult() {
        return result;
    }
}
