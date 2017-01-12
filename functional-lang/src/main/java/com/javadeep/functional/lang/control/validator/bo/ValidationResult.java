package com.javadeep.functional.lang.control.validator.bo;

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

    private List<ValidationError> errors = new LinkedList<>();

    private long timeElapsed;

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
     */
    public ValidationResult addErrors(ValidationError... errors) {
        Objects.requireNonNull(errors, "errors is null");
        return addErrors(Stream.of(errors));
    }

    /**
     * Add errors of {@code ValidationError}.
     *
     * @param errors A stream of {@code ValidationError}
     * @return The instance of {@code ValidationResult} itself.
     * @throws NullPointerException if {@code errors} is null.
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
     */
    public ValidationResult addErrors(Collection<ValidationError> errors) {
        Objects.requireNonNull(errors, "errors is null");
        this.errors.addAll(errors);
        return this;
    }

    /**
     *
     * Set the {@code timeElapsed} of validation
     *
     * @param timeElapsed The time elapsed of validation
     * @return The instance of {@code ValidationResult} itself.
     */
    public final ValidationResult timeElapsed(long timeElapsed) {
        this.timeElapsed = timeElapsed;
        return this;
    }

    public boolean isSuccess() {
        return errors.isEmpty();
    }


    public List<ValidationError> getErrors() {
        return errors;
    }

    public long getTimeElapsed() {
        return timeElapsed;
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(errors, timeElapsed);
    }

    @Override
    public String toString() {
        return String.format("ValidationResult{errors='%s', timeElapsed=%d}", errors.toString(), timeElapsed);
    }
}
