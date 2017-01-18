package com.javadeep.functional.lang.control.validator.jsr303;

import com.javadeep.functional.lang.control.validator.bo.ValidationError;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * JSR303 Based Hibernate Validator
 *
 * @author baojie
 * @since 1.0.0
 */
public final class HibernateSupportedValidator {

    private int errorCode;

    private final Validator hibernateValidator;

    private Function<ConstraintViolation, ValidationError> transformer = v -> ValidationError.of(v.getMessage())
            .field(v.getPropertyPath().toString())
            .invalidValue(v.getInvalidValue());

    /**
     * A Default Validator instance
     */
    private static Validator HIBERNATE_VALIDATOR;

    static {
        Locale.setDefault(Locale.ENGLISH);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        HIBERNATE_VALIDATOR = factory.getValidator();
    }

    /**
     * Constructs a {@code HibernateSupportedValidator}.
     *
     * @param hibernateValidator The {@code Validator}.
     */
    private HibernateSupportedValidator(Validator hibernateValidator) {
        this.hibernateValidator = hibernateValidator;
    }

    /**
     * Constructs a {@code HibernateSupportedValidator} by a default {@code Validator}
     *
     * @return a new {@code HibernateSupportedValidator} instance.
     */
    public static HibernateSupportedValidator build() {
        return buildByValidator(HIBERNATE_VALIDATOR);
    }

    /**
     * Constructs a {@code HibernateSupportedValidator} by a given {@code Validator}
     *
     * @param hibernateValidator A given {@code Validator}
     * @return a new {@code HibernateSupportedValidator} instance.
     * @throws NullPointerException if {@code hibernateValidator} is null.
     */
    public static HibernateSupportedValidator buildByValidator(Validator hibernateValidator) {
        Objects.requireNonNull(hibernateValidator);
        return new HibernateSupportedValidator(hibernateValidator);
    }

    /**
     * Set error code of Validator, default zero.
     *
     * @param errorCode The error code.
     * @return The instance of {@code HibernateSupportedValidator} itself.
     */
    public final HibernateSupportedValidator errorCode(int errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    /**
     * Set a transformer from {@code ConstraintViolation} to {@code ValidationError}
     *
     * @param transformer A transformer from {@code ConstraintViolation} to {@code ValidationError}
     * @return The instance of {@code HibernateSupportedValidator} itself.
     * @throws NullPointerException if {@code transformer} is null.
     */
    public final HibernateSupportedValidator transformer(Function<ConstraintViolation, ValidationError> transformer) {
        this.transformer = Objects.requireNonNull(transformer);
        return this;
    }

    /**
     * Get the validator function of this {@code HibernateSupportedValidator}.
     *
     * @param <T> Value type to be validated.
     * @return The function of this {@code HibernateSupportedValidator}.
     */
    public final <T> Function<T, Stream<ValidationError>> validator() {
        return t -> hibernateValidator.validate(t).stream().map(transformer).peek(v -> v.errorCode(errorCode));
    }
}
