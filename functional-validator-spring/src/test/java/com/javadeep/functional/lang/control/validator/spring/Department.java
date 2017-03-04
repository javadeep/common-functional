package com.javadeep.functional.lang.control.validator.spring;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Department
 */
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
