package com.cardpay.test.parser;

/**
 * File is not parsable
 */
public class FileNotParsableException extends Exception {

    public FileNotParsableException(Throwable e) {
        super("File is not parsable", e);
    }
}
