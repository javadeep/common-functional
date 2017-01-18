package com.javadeep.functional.lang.control.validator.jsr303;

import com.javadeep.functional.lang.control.validator.FunctionalValidator;
import org.hibernate.validator.constraints.Length;
import org.junit.Assert;
import org.junit.Test;

import javax.validation.constraints.NotNull;

/**
 * Test for {@linkplain HibernateSupportedValidator}
 */
public class HibernateSupportedValidatorTest {

    public class Department {

        @NotNull
        private final Integer id;

        @Length(max = 3)
        private final String name;

        public Department(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    @Test
    public void testHibernateSupportedValidator_success() {
        Department department = new Department(123, "bjb");
        Assert.assertTrue(FunctionalValidator.checkFrom(department)
                .on(HibernateSupportedValidator.build().validator())
                .doValidate()
                .isSuccess());
    }

    @Test
    public void testHibernateSupportedValidator_failure() {
        Department department = new Department(123, "bjbef");
        FunctionalValidator.checkFrom(department)
                .on(HibernateSupportedValidator
                        .build()
                        .errorCode(12345)
                        .validator())
                .doValidate()
                .getResult()
                .get()
                .getErrors()
                .forEach(error -> Assert.assertEquals(12345, error.getErrorCode()));
    }

    @Test
    public void testHibernateSupportedValidator_exception() {
        Department department = new Department(123, "bjbef");
        int id = department.getId();
        String name = department.getName();
        Assert.assertFalse(FunctionalValidator.checkFrom(department)
                .on(HibernateSupportedValidator
                        .build()
                        .errorCode(id)
                        .transformer(v -> {
                            throw new NullPointerException(name);
                        })
                        .validator())
                .doValidate()
                .isSuccess());
    }


}
