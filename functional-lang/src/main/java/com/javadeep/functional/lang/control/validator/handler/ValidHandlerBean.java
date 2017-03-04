package com.javadeep.functional.lang.control.validator.handler;

import com.javadeep.functional.lang.control.validator.bo.ValidationResult;
import com.javadeep.functional.lang.control.validator.exception.ValidationException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Validator Handler Bean
 *
 * @author baojie
 * @since 1.0.0
 */
public final class ValidHandlerBean {

    private final Object bean;
    private final Method method;

    /**
     * Construct the {@code ValidHandlerBean} instance
     *
     * @param bean bean.
     * @param method The method to be invoked.
     */
    private ValidHandlerBean(Object bean, Method method) {
        this.bean = bean;
        this.method = method;
    }

    /**
     * Construct the {@code ValidationExcepiton} instance.
     *
     * @param bean bean.
     * @param method The method to be invoked.
     * @throws NullPointerException if {@code bean} or {@code method} is null
     */
    public static ValidHandlerBean of(Object bean, Method method) {
        Objects.requireNonNull(bean, "bean is null");
        Objects.requireNonNull(method, "method is null");
        return new ValidHandlerBean(bean, method);
    }

    /**
     * Invoked the method to be executed.
     *
     * @param args The params of the {@code method}
     * @return the result of the method execution
     * @throws ValidationException if exception occurs.
     */
    public final Object invoke(Object... args) {
        try {
            return method.invoke(bean, args);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new ValidationException(ValidationResult.build().addGlobalError(e.getMessage()));
        }
    }
}
