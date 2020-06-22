package com.cardpay.test.models;

/**
 * Parsing result
 */
public enum ParsingResult {
    OK,
    AMOUNT_IS_EMPTY,
    AMOUNT_IS_NOT_A_NUMBER,
    ID_IS_EMPTY,
    ID_IS_NOT_A_NUMBER,
    CURRENCY_IS_EMPTY,
    INVALID_CURRENCY,
    COMMENT_IS_EMPTY,
    FILE_NOT_EXIST,
    UNSUPPORTED_FILE_FORMAT,
    FILE_NOT_PARSABLE,
}
