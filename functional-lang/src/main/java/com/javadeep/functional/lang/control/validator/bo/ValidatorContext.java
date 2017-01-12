package com.javadeep.functional.lang.control.validator.bo;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * The context of validator
 *
 * @author baojie
 * @since 1.0.0
 */
public final class ValidatorContext {

    private final ValidationResult result;

    /**
     * Constructs a {@code ValidationContext}.
     *
     * @param result A {@code ValidationResult}
     */
    private ValidatorContext(ValidationResult result) {
        this.result = result;
    }

    /**
     * Constructs a {@code ValidationContext} by {@code ValidationResult}.
     *
     * @param result A {@code ValidationResult}
     * @return The {@code ValidatorContext} instance.
     * @throws NullPointerException if {@code result} is null.
     */
    public static ValidatorContext of(ValidationResult result) {
        Objects.requireNonNull(result, "result is null");
        return new ValidatorContext(result);
    }

    /**
     * Add an error message.
     *
     * @param msg a error message.
     * @return The {@code ValidatorContext} instance itself.
     * @throws NullPointerException if {@code msg} is null.
     */
    public final ValidatorContext addErrorMsg(String msg) {
        Objects.requireNonNull(msg, "msg is null");
        result.addError(ValidationError.of(msg));
        return this;
    }

    /**
     * Add error messages
     *
     * @param msgs The array of error message.
     * @return The {@code ValidatorContext} instance itself.
     * @throws NullPointerException if {@code msgs} is null.
     * @throws IllegalArgumentException if {@code msgs} is empty.
     */
    public final ValidatorContext addErrorMsgs(String... msgs) {
        Objects.requireNonNull(msgs, "msgs is null");
        return addErrorMsgs(Stream.of(msgs));
    }

    /**
     * Add error messages
     *
     * @param msgs A stream of error message.
     * @return The {@code ValidatorContext} instance itself.
     * @throws NullPointerException if {@code msgs} is null.
     */
    public final ValidatorContext addErrorMsgs(Stream<String> msgs) {
        Objects.requireNonNull(msgs, "msgs is null");
        result.addErrors(msgs.map(ValidationError::of));
        return this;
    }

    /**
     * Add error messages
     *
     * @param msgs A collection of error message.
     * @return The {@code ValidatorContext} instance itself.
     * @throws NullPointerException if {@code msgs} is null.
     */
    public final ValidatorContext addErrorMsgs(Collection<String> msgs) {
        Objects.requireNonNull(msgs, "msgs is null");
        return addErrorMsgs(msgs.stream());
    }

    /**
     * Add an error of {@code ValidationError}
     *
     * @param error The {@code ValidationError}
     * @return The {@code ValidatorContext} instance itself.
     * @throws NullPointerException if {@code error} is null
     */
    public final ValidatorContext addError(ValidationError error) {
        Objects.requireNonNull(error, "error is null");
        result.addError(error);
        return this;
    }

    /**
     * Add errors of {@code ValidationError}.
     *
     * @param errors The array of {@code ValidationError}
     * @return The instance of {@code ValidationContext} itself.
     * @throws NullPointerException if {@code errors} is null.
     */
    public final ValidatorContext addErrors(ValidationError... errors) {
        Objects.requireNonNull(errors, "errors is null");
        result.addErrors(errors);
        return this;
    }

    /**
     * Add errors of {@code ValidationError}.
     *
     * @param errors A stream of {@code ValidationError}
     * @return The instance of {@code ValidationContext} itself.
     * @throws NullPointerException if {@code errors} is null.
     */
    public final ValidatorContext addErrors(Stream<ValidationError> errors) {
        Objects.requireNonNull(errors, "errors is null");
        result.addErrors(errors);
        return this;
    }

    /**
     * Add errors of {@code ValidationError}.
     *
     * @param errors A collection of {@code ValidationError}
     * @return The instance of {@code ValidationContext} itself.
     * @throws NullPointerException if {@code errors} is null.
     */
    public final ValidatorContext addErrors(Collection<ValidationError> errors) {
        Objects.requireNonNull(errors, "errors is null");
        result.addErrors(errors);
        return this;
    }

    public ValidationResult getResult() {
        return result;
    }



    @Override
    public boolean equals(Object obj) {
        return obj == this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(result);
    }

    @Override
    public String toString() {
        return String.format("ValidationContext{result=%s}", result.toString());
    }
}
