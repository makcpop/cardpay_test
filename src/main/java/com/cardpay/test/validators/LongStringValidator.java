package com.cardpay.test.validators;

import java.util.Collection;

/**
 * Checks that the String value has long format.
 * Otherwise add validation result to collection
 *
 * @param <E> type of validate result value
 */
public class LongStringValidator<E> implements Validator<String, E> {

    private final E notLongValueResult;

    public LongStringValidator(E notLongValueResult) {
        this.notLongValueResult = notLongValueResult;
    }

    @Override
    public void validate(String data, Collection<E> result) {
        try {
            Long.parseLong(data);
        } catch (Throwable e) {
            result.add(notLongValueResult);
        }
    }
}
