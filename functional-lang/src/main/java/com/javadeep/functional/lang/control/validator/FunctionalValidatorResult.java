package com.javadeep.functional.lang.control.validator;

import com.javadeep.functional.lang.control.validator.bo.ValidationResult;
import com.javadeep.functional.lang.data.Try;

import java.util.function.Function;

/**
 * Created by baojie02 on 17/1/12.
 */
public final class FunctionalValidatorResult<T> {

    private final T element;
    private final Try<ValidationResult> result;

    private FunctionalValidatorResult(T element, Try<ValidationResult> result) {
        this.element = element;
        this.result = result;
    }

    public static <T> FunctionalValidatorResult<T> of(T element, Try<ValidationResult> result) {
        return new FunctionalValidatorResult<>(element, result);
    }

    public final <U> U fold(Function<? super T, ? extends U> successMapper,
                            Function<? super ValidationResult, ? extends U> failureMapper,
                            Function<? super Throwable, ? extends U> exceptionMapper) {
        return result.fold(res -> res.isSuccess() ? successMapper.apply(element) : failureMapper.apply(res),
                exceptionMapper);
    }

    public T getElement() {
        return element;
    }

    public Try<ValidationResult> getResult() {
        return result;
    }
}
