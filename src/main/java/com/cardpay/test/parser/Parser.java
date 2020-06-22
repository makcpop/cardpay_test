package com.cardpay.test.parser;

import com.cardpay.test.models.RawData;

import java.util.Collection;

/**
 * Interface for parsing file
 */
public interface Parser {

    /**
     * Check that the implemented parser is applicable to specified file;
     *
     * @param fileName file name
     * @return true if the implemented parser is applicable to specified file
     */
    boolean isApplicable(String fileName);

    /**
     * Parse file and return raw data
     *
     * @param fileName file name
     * @return raw data
     * @throws FileNotParsableException if some error happenes during file parsing
     */
    Collection<RawData> parse(String fileName) throws FileNotParsableException;
}
