package com.javadeep.functional.lang.control.validator.bo;

import org.junit.Assert;
import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Test for {@linkplain ValidatorContext}
 */
public class ValidatorContextTest {

    @Test
    public void testAddError() {
        ValidatorContext context = ValidatorContext.of(ValidationResult.build())
                .addErrorMsg("message1")
                .addErrorMsg("message2")
                .addErrorMsgs("message3", "message4")
                .addError(ValidationError.builder("message5").build())
                .addErrors(ValidationError.builder("message6").build())
                .addErrorMsgs(Stream.of("message7").collect(Collectors.toList()))
                .addErrorMsgs(Stream.of("message8"))
                .addErrors(Stream.of(ValidationError.builder("message9").build()))
                .addErrors(Stream.of(ValidationError.builder("message10").build()).collect(Collectors.toList()));
        Assert.assertEquals(10, context.getResult().getErrors().size());
    }
}
