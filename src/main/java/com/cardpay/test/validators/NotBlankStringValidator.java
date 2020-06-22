package com.cardpay.test.validators;

import java.util.Collection;

/**
 * Checks that the String value is not blank and delegate validation to the delegate validator, if delegate is not null.
 * Otherwise add validation result to collection and stop validating
 *
 * @param <E> type of validate result value
 */
public class NotBlankStringValidator<E> implements Validator<String, E> {

    private final Validator<String, E> delegate;
    private final E blankStringResult;

    public NotBlankStringValidator(
            Validator<String, E> delegate,
            E blankStringResult) {
        this.delegate = delegate;
        this.blankStringResult = blankStringResult;
    }
    public NotBlankStringValidator(
            E blankStringResult) {
        this.delegate = null;
        this.blankStringResult = blankStringResult;
    }

    @Override
    public void validate(String data, Collection<E> result) {
        if (data == null || data.isEmpty()) {
            result.add(blankStringResult);
        } else if (delegate != null){
            delegate.validate(data, result);
        }
    }
}
