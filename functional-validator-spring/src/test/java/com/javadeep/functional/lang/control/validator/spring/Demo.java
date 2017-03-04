package com.javadeep.functional.lang.control.validator.spring;

import com.javadeep.functional.lang.control.validator.Annotation.Valid;
import org.springframework.stereotype.Component;

/**
 * Demo
 */
@Component
public class Demo {

    @Valid(DemoValidator.class)
    public boolean add(Department department) {
        return department != null;
    }
}
