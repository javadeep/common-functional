package com.javadeep.functional.lang.control.validator.spring;

import com.javadeep.functional.lang.control.validator.Annotation.Valid;
import com.javadeep.functional.lang.control.validator.bo.ValidationError;
import com.javadeep.functional.lang.control.validator.bo.ValidationResult;
import com.javadeep.functional.lang.control.validator.exception.ValidationException;
import com.javadeep.functional.lang.control.validator.handler.ValidHandlerBean;
import com.javadeep.functional.lang.control.validator.handler.ValidHandlerFactory;
import com.javadeep.functional.lang.control.validator.jsr303.HibernateSupportedValidator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * The aspect of Validator.
 *
 * @author baojie
 * @since 1.0.0
 */
@Component
@Aspect
public class ValidAspect {

    /**
     * Before aspect of method annotation {@Valid}.
     *
     * @param point The {@code JointPoint}
     * @param valid The annotation {@code valid}.
     * @throws ValidationException if validation is fail.
     */
    @Before(value = "@annotation(valid)")
    public void beforeExecution(JoinPoint point, Valid valid) {
        Object[] arguments = point.getArgs();
        boolean failFast = valid.failFast();
        boolean hibernateValidate = valid.hibernateValidate();
        int hibernateErrorCode = valid.hibernateErrorCode();
        Class<?> value = valid.value();
        String handlerName = valid.method().isEmpty() ? point.getSignature().getName() : valid.method();
        ValidationResult result = ValidationResult.build();
        if (hibernateValidate) {
            // Hibernate Validator
            javax.validation.Validator hibernateValidator = failFast ?
                    HibernateSupportedValidator.FAILFAST_VALIDATOR : HibernateSupportedValidator.FAILOVER_VALIDATOR;
            for (Object argument : arguments) {
                result.addErrors(hibernateValidator.validate(argument).stream()
                        .map(v -> ValidationError.of(v.getPropertyPath().toString(), v.getMessage())
                                .invalidValue(v.getInvalidValue())
                                .errorCode(hibernateErrorCode)));

                if (failFast && !result.isSuccess()) {
                    throw new ValidationException(result);
                }
            }

            // Annotation Valid Validator
            if (!value.equals(Void.class)) {
                ValidHandlerBean handler = ValidHandlerFactory.getHandler(handlerName, value)
                        .orElseThrow(() -> new RuntimeException("validHandler not found"));
                Object[] newArguments = new Object[arguments.length + 1];
                newArguments[0] = result;
                System.arraycopy(arguments, 0, newArguments, 1, arguments.length);
                handler.invoke(newArguments);
                if (!result.isSuccess()) {
                    throw new ValidationException(result);
                }
            }
        }
    }
}
