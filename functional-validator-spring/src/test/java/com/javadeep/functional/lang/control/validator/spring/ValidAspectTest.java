package com.javadeep.functional.lang.control.validator.spring;

import com.javadeep.functional.lang.control.validator.exception.ValidationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Test for {@linkplain ValidAspect}
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ValidAspectTest {

    @Resource
    private Demo demo;

    @Test(expected = ValidationException.class)
    public void testValidAspect() {
        Department department = new Department(0, "aaa");
        demo.add(department);
        Assert.assertEquals(Integer.valueOf(0), department.getId());
        Assert.assertEquals("aaa", department.getName());
    }

    public Demo getDemo() {
        return demo;
    }

    public void setDemo(Demo demo) {
        this.demo = demo;
    }
}
