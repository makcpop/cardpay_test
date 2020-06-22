package com.cardpay.test.validators;

import java.util.Collection;
import java.util.Currency;

/**
 * Checks that the String value has ISO 4217 currency format.
 * Otherwise add validation result to collection
 *
 * @param <E> type of validate result value
 */
public class CurrencyStringValidator<E> implements Validator<String, E> {

    private final E notCurrencyValueResult;

    public CurrencyStringValidator(E notCurrencyValueResult) {
        this.notCurrencyValueResult = notCurrencyValueResult;
    }

    @Override
    public void validate(String data, Collection<E> result) {
        try {
            Currency.getInstance(data);
        } catch (Throwable e) {
            result.add(notCurrencyValueResult);
        }
    }
}
