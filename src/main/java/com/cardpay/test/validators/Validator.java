package com.cardpay.test.validators;

import java.util.Collection;

/**
 * Common interface for validators
 * @param <T> type of validating data
 * @param <E> type of validate result value
 */
public interface Validator<T, E> {

    /**
     * Validate data
     *
     * @param data data for validation
     * @param result validation result collection
     */
    void validate(T data, Collection<E> result);
}
