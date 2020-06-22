package com.cardpay.test.validators;

import com.cardpay.test.models.ParsingResult;
import com.cardpay.test.models.RawData;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class RawDataValidatorTest {

    private final RawDataValidator validator = new RawDataValidator();

    @Test
    public void testCorrectValue() {
        List<ParsingResult> result = new ArrayList<>();
        RawData data = RawData.builder()
                .id("1")
                .amount("100")
                .currency("USD")
                .comment("Some text")
                .build();
        validator.validate(data, result);
        assertThat(result, is(empty()));
    }

    @Test
    public void testNullData() {
        List<ParsingResult> result = new ArrayList<>();
        RawData data = RawData.builder()
                .build();
        validator.validate(data, result);
        assertThat(result, contains(ParsingResult.ID_IS_EMPTY, ParsingResult.AMOUNT_IS_EMPTY,
                ParsingResult.CURRENCY_IS_EMPTY, ParsingResult.COMMENT_IS_EMPTY));
    }

    @Test
    public void testEmptyStringData() {
        List<ParsingResult> result = new ArrayList<>();
        RawData data = RawData.builder()
                .id("")
                .amount("")
                .currency("")
                .comment("")
                .build();
        validator.validate(data, result);
        assertThat(result, contains(ParsingResult.ID_IS_EMPTY, ParsingResult.AMOUNT_IS_EMPTY,
                ParsingResult.CURRENCY_IS_EMPTY, ParsingResult.COMMENT_IS_EMPTY));
    }

    @Test
    public void testNotCorrectData() {
        List<ParsingResult> result = new ArrayList<>();
        RawData data = RawData.builder()
                .id("a")
                .amount("aa")
                .currency("AAA")
                .comment("cooment")
                .build();
        validator.validate(data, result);
        assertThat(result, contains(ParsingResult.ID_IS_NOT_A_NUMBER, ParsingResult.AMOUNT_IS_NOT_A_NUMBER,
                ParsingResult.INVALID_CURRENCY));
    }
}