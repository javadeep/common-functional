package com.javadeep.functional.lang.control.validator.bo;

import com.javadeep.functional.lang.control.base.Preconditions;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The result of validation.
 *
 * @author baojie
 * @since 1.0.0
 */
public final class ValidationResult {

    private boolean isSuccess = true;

    private List<ValidationError> errors = new LinkedList<>();

    private int timeElapsed;

    /**
     * Constructs a {@code ValidationResult}.
     */
    private ValidationResult() {

    }

    /**
     * Constructs a {@code ValidationResult}.
     *
     * @return The instance of {@code ValidationResult}.
     */
    public static ValidationResult build() {
        return new ValidationResult();
    }

    /**
     * Add an {@code ValidationError}
     *
     * @param error The {@code ValidationError}
     * @return The instance of {@code ValidationResult} itself.
     * @throws NullPointerException if {@code error} is null
     */
    public final ValidationResult addError(ValidationError error) {
        Objects.requireNonNull(error, "error is null");
        errors.add(error);
        return this;
    }

    /**
     * Add errors of {@code ValidationError}.
     *
     * @param errors The array of {@code ValidationError}
     * @return The instance of {@code ValidationResult} itself.
     * @throws NullPointerException if {@code errors} is null.
     * @throws IllegalArgumentException if {@code errors} is empty.
     */
    public ValidationResult addErrors(ValidationError... errors) {
        Preconditions.checkNotEmpty(errors, "errors is empty");
        return addErrors(Stream.of(errors));
    }

    /**
     * Add errors of {@code ValidationError}.
     *
     * @param errors A stream of {@code ValidationError}
     * @return The instance of {@code ValidationResult} itself.
     * @throws NullPointerException if {@code errors} is null.
     * @throws IllegalArgumentException if {@code errors} is empty.
     */
    public ValidationResult addErrors(Stream<ValidationError> errors) {
        Objects.requireNonNull(errors, "errors is null");
        return addErrors(errors.collect(Collectors.toList()));
    }

    /**
     * Add errors of {@code ValidationError}.
     *
     * @param errors A collection of {@code ValidationError}
     * @return The instance of {@code ValidationResult} itself.
     * @throws NullPointerException if {@code errors} is null.
     * @throws IllegalArgumentException if {@code errors} is empty.
     */
    public ValidationResult addErrors(Collection<ValidationError> errors) {
        Preconditions.checkNotEmpty(errors, "errors is empty");
        this.errors.addAll(errors);
        return this;
    }

    /**
     * Set the result of validation is success.
     *
     * @return The instance of {@code ValidationResult} itself.
     */
    public final ValidationResult success() {
        this.isSuccess = true;
        return this;
    }

    /**
     * Set the result of validation is not success.
     *
     * @return The instance of {@code ValidationResult} itself.
     */
    public final ValidationResult failure() {
        this.isSuccess = false;
        return this;
    }

    /**
     *
     * Set the {@code timeElapsed} of validation
     *
     * @param timeElapsed The time elapsed of validation
     * @return The instance of {@code ValidationResult} itself.
     */
    public final ValidationResult timeElapsed(int timeElapsed) {
        this.timeElapsed = timeElapsed;
        return this;
    }

    public boolean isSuccess() {
        return isSuccess;
    }


    public List<ValidationError> getErrors() {
        return errors;
    }

    public int getTimeElapsed() {
        return timeElapsed;
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isSuccess, errors, timeElapsed);
    }

    @Override
    public String toString() {
        return String.format("ValidationResult{isSuccess=%s, errors='%s', timeElapsed=%d}",
                String.valueOf(isSuccess), errors.toString(), timeElapsed);
    }
}
