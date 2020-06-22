package com.cardpay.test.validators;

import com.cardpay.test.models.ParsingResult;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

class LongStringValidatorTest {

    private final LongStringValidator<ParsingResult> validator = new LongStringValidator<>(
            ParsingResult.AMOUNT_IS_NOT_A_NUMBER);

    @Test
    public void testLongValue() {
        List<ParsingResult> result = new ArrayList<>();
        validator.validate("1000", result);
        assertThat(result, is(empty()));
    }

    @Test
    public void testNotLongValue() {
        List<ParsingResult> result = new ArrayList<>();
        validator.validate("10asd00", result);
        assertThat(result, contains(ParsingResult.AMOUNT_IS_NOT_A_NUMBER));
    }
}