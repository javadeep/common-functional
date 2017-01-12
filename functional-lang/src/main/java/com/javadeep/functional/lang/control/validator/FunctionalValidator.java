package com.javadeep.functional.lang.control.validator;

import com.javadeep.functional.lang.control.validator.bo.ValidationError;
import com.javadeep.functional.lang.control.validator.bo.ValidationResult;
import com.javadeep.functional.lang.data.Try;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * A functional Chained call validator
 *
 * @author baojie
 * @since 1.0.0
 */
public final class FunctionalValidator<T> {

    private final T element;
    private boolean isFailFast = true;
    private final List<Function<T, Stream<ValidationError>>> validators = new LinkedList<>();

    /**
     * Constructs a {@code FunctionalValidator}.
     *
     * @param element The element to be checked.
     */
    private FunctionalValidator(T element) {
        this.element = element;
    }

    /**
     * Constructs a {@code FunctionalValidator} from an element.
     *
     * @param element The element to be checked
     * @param <T> The type of element to be checked
     * @return a new {@code FunctionalValidator} instance.
     */
    public static <T> FunctionalValidator<T> checkFrom(T element) {

        return new FunctionalValidator<>(element);
    }

    /**
     * The method to prevent the following validators from getting validated if any validator fails.
     *
     * @return the instance of {@code FunctionalValidator} itself.
     */
    public final FunctionalValidator<T> failFast() {
        isFailFast = true;
        return this;
    }

    /**
     * The method to ignore the failures so that all the validators will work in order.
     *
     * @return the instance of {@code FunctionalValidator} itself.
     */
    public final FunctionalValidator<T> failOver() {
        isFailFast = false;
        return this;
    }

    public final FunctionalValidator<T> on(Function<T, Stream<ValidationError>> v) {
        Objects.requireNonNull(v, "v is null");
        validators.add(v);
        return this;
    }

    public final FunctionalValidator<T> on(Predicate<T> validatorPredicate, String errorMsg) {
        Objects.requireNonNull(validatorPredicate, "validatorPredicate is null");
        Objects.requireNonNull(errorMsg, "errorMsg is null");

        validators.add(t -> validatorPredicate.test(t) ? Stream.empty() : Stream.of(ValidationError.of(errorMsg)));

        return this;
    }

    public final FunctionalValidator<T> on(Predicate<T> validatorPredicate, ValidationError error) {
        Objects.requireNonNull(validatorPredicate, "validatorPredicate is null");
        Objects.requireNonNull(error, "error is null");

        validators.add(t -> validatorPredicate.test(t) ? Stream.empty() : Stream.of(error));

        return this;
    }

    public final FunctionalValidator<T> on(Predicate<T> validatorPredicate, Stream<ValidationError> errors) {
        Objects.requireNonNull(validatorPredicate, "validatorPredicate is null");
        Objects.requireNonNull(errors, "errors is null");

        validators.add(t -> validatorPredicate.test(t) ? Stream.empty() : errors);

        return this;
    }

    public final FunctionalValidatorResult<T> doValidate() {

        long start = System.currentTimeMillis();
        ValidationResult result = ValidationResult.build();

        try {
            if (isFailFast) {
                for (Function<T, Stream<ValidationError>> validator : validators) {
                    result.addErrors(validator.apply(element));
                    if (!result.isSuccess()) {
                        break;
                    }
                }
            } else {
                validators.forEach(v -> result.addErrors(v.apply(element)));

            }
            return FunctionalValidatorResult.of(element,
                    Try.of(() -> result.timeElapsed(System.currentTimeMillis() - start)));
        } catch (Throwable e) {
            return FunctionalValidatorResult.of(element, Try.failure(e));
        }
    }
}
