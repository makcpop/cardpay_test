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

class CurrencyStringValidatorTest {

    public final CurrencyStringValidator<ParsingResult> currencyStringValidator = new CurrencyStringValidator<>(
            ParsingResult.INVALID_CURRENCY
    );

    @Test
    public void testCorrectCurrency() {
        List<ParsingResult> result = new ArrayList<>();
        currencyStringValidator.validate("USD", result);
        assertThat(result, is(empty()));
    }

    @Test
    public void testIncorrectCurrency() {
        List<ParsingResult> result = new ArrayList<>();
        currencyStringValidator.validate("UUU", result);
        assertThat(result, contains(ParsingResult.INVALID_CURRENCY));
    }
}