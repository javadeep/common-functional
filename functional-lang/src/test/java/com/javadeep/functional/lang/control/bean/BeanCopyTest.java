package com.javadeep.functional.lang.control.bean;


import org.junit.Assert;
import org.junit.Test;

/**
 * Test for {@linkplain BeanCopy}
 */
public class BeanCopyTest {

    public class Department {

        private Integer id;

        private String name;

        public Department() {

        }

        public Department(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    @Test
    public void testBeanCopy() {
        Department sourceDepartment = new Department(1, "name");
        Department targetDepartment = BeanCopy.of(sourceDepartment, new Department())
                .copy((s, t) -> {
                    t.setId(s.getId());
                    t.setName(s.getName());
                })
                .get();
        Assert.assertEquals(Integer.valueOf(1), targetDepartment.getId());
        Assert.assertEquals("name", targetDepartment.getName());
    }
}
