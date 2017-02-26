package com.javadeep.functional.lang.control.base;

import com.javadeep.functional.lang.control.base.annotation.Value;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

/**
 * Test for {@linkplain Enums}
 */
public class EnumsTest {

    public enum MyType {

        MESSAGE1("message1"),
        MESSAGE2("message2");

        private final String message;

        MyType(String message) {
            this.message = message;
        }

        @Value
        public String getMessage() {
            return message;
        }
    }

    public enum MyType2 {

        MESSAGE1("message1", 1),
        MESSAGE2("message5", 5);

        private final String message;
        private final int value;

        MyType2(String message, int value) {
            this.message = message;
            this.value = value;
        }

        @Value
        public String getMessage() {
            return message;
        }

        @Value
        public int getValue() {
            return value;
        }
    }

    public enum MyType3 {

        MESSAGE1("message1");

        private final String message;

        MyType3(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    @Test
    public void testFind() {
        Assert.assertEquals("message1", MyType.MESSAGE1.getMessage());
        Assert.assertEquals("message1", MyType2.MESSAGE1.getMessage());
        Assert.assertEquals(1, MyType2.MESSAGE1.getValue());
        Assert.assertEquals("message1", MyType3.MESSAGE1.getMessage());
        Assert.assertEquals(Optional.of(MyType.MESSAGE1), Enums.find("message1", MyType.class));
        Assert.assertEquals(Optional.of(MyType.MESSAGE2), Enums.find("message2", MyType.class));
        Assert.assertEquals(Optional.of(MyType2.MESSAGE1), Enums.find("message1", MyType2.class));
        Assert.assertEquals(Optional.of(MyType2.MESSAGE2), Enums.find("message5", MyType2.class));
        Assert.assertEquals(Optional.of(MyType2.MESSAGE1), Enums.find(1, MyType2.class));
        Assert.assertEquals(Optional.empty(), Enums.find("message2", MyType2.class));
        Assert.assertEquals(Optional.empty(), Enums.find(2, MyType2.class));
        Assert.assertEquals(Optional.empty(), Enums.find("message1", MyType3.class));
    }
}
