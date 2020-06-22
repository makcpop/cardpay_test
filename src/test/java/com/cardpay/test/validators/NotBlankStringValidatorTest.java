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

class NotBlankStringValidatorTest {

    @Test
    public void testNullString_withoutChain() {
        NotBlankStringValidator<ParsingResult> validator = new NotBlankStringValidator<>(
                ParsingResult.ID_IS_EMPTY);
        List<ParsingResult> result = new ArrayList<>();
        validator.validate(null, result);
        assertThat(result, contains(ParsingResult.ID_IS_EMPTY));
    }

    @Test
    public void testEmptyString_withoutChain() {
        NotBlankStringValidator<ParsingResult> validator = new NotBlankStringValidator<>(
                ParsingResult.ID_IS_EMPTY);
        List<ParsingResult> result = new ArrayList<>();
        validator.validate("", result);
        assertThat(result, contains(ParsingResult.ID_IS_EMPTY));
    }

    @Test
    public void testCorrectString_withoutChain() {
        NotBlankStringValidator<ParsingResult> validator = new NotBlankStringValidator<>(
                ParsingResult.ID_IS_EMPTY);
        List<ParsingResult> result = new ArrayList<>();
        validator.validate("text", result);
        assertThat(result, is(empty()));
    }

    @Test
    public void testNullString_withChain() {
        NotBlankStringValidator<ParsingResult> validator = new NotBlankStringValidator<>(
                (v, c) -> fail(),
                ParsingResult.ID_IS_EMPTY);
        List<ParsingResult> result = new ArrayList<>();
        validator.validate(null, result);
        assertThat(result, contains(ParsingResult.ID_IS_EMPTY));
    }

    @Test
    public void testEmptyString_withChain() {
        NotBlankStringValidator<ParsingResult> validator = new NotBlankStringValidator<>(
                (v, c) -> fail(),
                ParsingResult.ID_IS_EMPTY);
        List<ParsingResult> result = new ArrayList<>();
        validator.validate("", result);
        assertThat(result, contains(ParsingResult.ID_IS_EMPTY));
    }

    @Test
    public void testCorrectString_withChain() {
        boolean[] chaiWasCalled = new boolean[] {false};
        NotBlankStringValidator<ParsingResult> validator = new NotBlankStringValidator<>(
                (v, c) -> chaiWasCalled[0] = true,
                ParsingResult.ID_IS_EMPTY);
        List<ParsingResult> result = new ArrayList<>();
        validator.validate("text", result);
        assertThat(result, is(empty()));
        assertTrue(chaiWasCalled[0]);
    }


}