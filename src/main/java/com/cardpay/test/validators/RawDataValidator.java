package com.cardpay.test.validators;

import com.cardpay.test.models.RawData;
import com.cardpay.test.models.ParsingResult;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * RawData validator
 */
@Service
public class RawDataValidator implements Validator<RawData, ParsingResult> {

    private final List<Validator<RawData, ParsingResult>> validators;

    public RawDataValidator() {
        this.validators = Arrays.asList(
            new FieldValidatior<>(
                    RawData::getId,
                    new NotBlankStringValidator<>(
                            new LongStringValidator<>(ParsingResult.ID_IS_NOT_A_NUMBER),
                            ParsingResult.ID_IS_EMPTY
                    )),
            new FieldValidatior<>(
                    RawData::getAmount,
                    new NotBlankStringValidator<>(
                            new LongStringValidator<>(ParsingResult.AMOUNT_IS_NOT_A_NUMBER),
                            ParsingResult.AMOUNT_IS_EMPTY
                    )),
            new FieldValidatior<>(
                    RawData::getCurrency,
                    new NotBlankStringValidator<>(
                            new CurrencyStringValidator<>(ParsingResult.INVALID_CURRENCY),
                            ParsingResult.CURRENCY_IS_EMPTY
                    )),
            new FieldValidatior<>(
                    RawData::getComment,
                    new NotBlankStringValidator<>(
                            ParsingResult.COMMENT_IS_EMPTY
                    ))
        );
    }

    @Override
    public void validate(RawData data, Collection<ParsingResult> result) {
        for (Validator<RawData, ParsingResult> validator : validators) {
            validator.validate(data, result);
        }
    }
}
