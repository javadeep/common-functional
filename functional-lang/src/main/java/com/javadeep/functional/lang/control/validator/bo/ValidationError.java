package com.javadeep.functional.lang.control.validator.bo;

import java.util.Objects;

/**
 * Errors of validation result.
 *
 * @author baojie
 * @since 1.0.0
 */
public final class ValidationError {

    private final String errorMsg;
    private String field;
    private int errorCode;
    private Object invalidValue;

    /**
     * Constructs a {@code ValidationError} from {@code errorMsg}.
     *
     * @param errorMsg The error message
     */
    private ValidationError(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * Constructs a {@code ValidationError} from {@code errorMsg}.
     *
     * @param errorMsg The error message.
     * @return The {@code ValidationError} instance.
     * @throws NullPointerException if {@code errorMsg} is null
     */
    public static ValidationError of(String errorMsg) {
        Objects.requireNonNull(errorMsg);
        return new ValidationError(errorMsg);
    }

    /**
     * Set feild name.
     *
     * @param field The field name.
     * @return The {@code ValidationError} instance.
     * @throws NullPointerException if {@code field} is null
     */
    public final ValidationError field(String field) {
        this.field = Objects.requireNonNull(field, "field is null");
        return this;
    }

    /**
     * Set error code.
     *
     * @param errorCode The error code.
     * @return The {@code ValidationError} instance.
     */
    public final ValidationError errorCode(int errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    /**
     * Set invalid value object.
     *
     * @param invalidValue The invalid object.
     * @return The {@code ValidationError} instance.
     */
    public final ValidationError invalidValue(Object invalidValue) {
        this.invalidValue = invalidValue;
        return this;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String getField() {
        return field;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public Object getInvalidValue() {
        return invalidValue;
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorMsg, field, errorCode, invalidValue);
    }

    @Override
    public String toString() {
        return String.format("ValidationError{errorMsg='%s', errorCode=%d, field='%s', invalidValue=%s}",
                errorMsg, errorCode, field, invalidValue);
    }
}
