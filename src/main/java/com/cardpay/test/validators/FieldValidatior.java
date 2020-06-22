package com.cardpay.test.validators;

import java.util.Collection;
import java.util.function.Function;

/**
 * Validator validates object field;
 *
 * @param <T> object type
 * @param <S> field type
 * @param <E> type of validate result value
 */
public class FieldValidatior<T, S, E> implements Validator<T, E> {

    private final Function<T, S> getFieldFunction;
    private final Validator<S, E> validator;

    public FieldValidatior(
            Function<T, S> getFieldFunction,
            Validator<S, E> validator) {
        this.getFieldFunction = getFieldFunction;
        this.validator = validator;
    }

    @Override
    public void validate(T data, Collection<E> result) {
        validator.validate(
                getFieldFunction.apply(data),
                result
        );
    }
}
