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
    private final String field;
    private final int errorCode;
    private final Object invalidValue;

    /**
     * Builder of {@code ValidationError}
     *
     * @author baojie
     * @since 1.0.0
     */
    public static class Builder {

        private final String errorMsg;
        private String field;
        private int errorCode;
        private Object invalidValue;

        /**
         * Constructs a {@code Builder}.
         *
         * @param errorMsg The error message
         * @throws NullPointerException if {@code errorMsg} is null
         */
        private Builder(String errorMsg) {
            this.errorMsg = Objects.requireNonNull(errorMsg);
        }

        /**
         * Set feild name.
         *
         * @param field The field name.
         * @return The {@code Builder} instance.
         * @throws NullPointerException if {@code field} is null
         */
        public final Builder field(String field) {
            this.field = Objects.requireNonNull(field);
            return this;
        }

        /**
         * Set error code.
         *
         * @param errorCode The error code.
         * @return The {@code Builder} instance.
         */
        public final Builder errorCode(int errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        /**
         * Set invalid value object.
         *
         * @param invalidValue The invalid object.
         * @return The {@code Builder} instance.
         */
        public final Builder invalidValue(Object invalidValue) {
            this.invalidValue = invalidValue;
            return this;
        }

        /**
         * Constructs a {@code ValidationError}.
         *
         * @return The {@code ValidationError} instance.
         */
        public final ValidationError build() {
            return new ValidationError(this);
        }
    }

    /**
     * Constructs a {@code ValidationError} from {@code builder}.
     *
     * @param builder The {@code builder} of {@code ValidationError}
     */
    private ValidationError(Builder builder) {
        this.errorMsg = builder.errorMsg;
        this.field = builder.field;
        this.errorCode = builder.errorCode;
        this.invalidValue = builder.invalidValue;
    }

    /**
     * Constructs a {@code Builder} from {@code errorMsg}.
     *
     * @param errorMsg The error message.
     * @return The {@code Builder} instance.
     */
    public static Builder builder(String errorMsg) {
        return new Builder(errorMsg);
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
