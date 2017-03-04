package com.javadeep.functional.lang.control.validator.Annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotate the target method need to be validated.
 *
 * @author baojie
 * @since 1.0.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Valid {

    /**
     *
     * The target class of the validator, default {@code Void} indicates no Validator
     *
     */
    Class<?> value() default Void.class;

    /**
     * The key of the validator,
     * default empty string indicates the key is equal to the target method to be validated.
     */
    String method() default "";

    /**
     * Indicates whether to prevent the following validators from getting validated if any validator fails.
     */
    boolean failFast() default true;

    /**
     * Indicates whether do hibernate validator first.
     */
    boolean hibernateValidate() default true;

    /**
     * The error code of Validator, default zero.
     */
    int hibernateErrorCode() default 0;
}
