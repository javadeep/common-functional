package com.javadeep.functional.lang.control.validator.Annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotate the target method is a validator to validate the other method which params is valid or not.
 *
 * @author baojie
 * @since 1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidHandler {

    /**
     * The key of the validator,
     * default empty string indicates the key is equal to the target method.
     */
    String value() default "";
}
