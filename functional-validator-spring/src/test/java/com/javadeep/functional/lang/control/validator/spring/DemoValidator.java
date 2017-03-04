package com.javadeep.functional.lang.control.validator.spring;

import com.javadeep.functional.lang.control.validator.Annotation.ValidHandler;
import com.javadeep.functional.lang.control.validator.bo.ValidationResult;
import org.springframework.stereotype.Component;

/**
 * DemoValidator
 */
@Component
public class DemoValidator {

    @ValidHandler
    @SuppressWarnings("unused")
    public void add(ValidationResult result, Department department) {
        if (department != null) {
            result.addGlobalError("department");
        }
    }
}
