package com.cardpay.test.parser;

import com.cardpay.test.models.RawData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Collection;
import java.util.Collections;

/**
 * Json files parser
 */
@Service
public class JsonParser implements Parser {

    private final ObjectMapper objectMapper;

    @Autowired
    public JsonParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean isApplicable(String fileName) {
        return fileName.endsWith(".json");
    }

    @Override
    public Collection<RawData> parse(String fileName) throws FileNotParsableException{
        try {
            DataLine dataLine = objectMapper.readValue(new File(fileName), DataLine.class);
            return Collections.singleton(RawData.builder()
                    .id(dataLine.getOrderId())
                    .amount(dataLine.getAmount())
                    .currency(dataLine.getCurrency())
                    .comment(dataLine.getComment())
                    .fileName(fileName)
                    .line(1)
                    .build());
        } catch (Exception e) {
            throw new FileNotParsableException(e);
        }
    }
}
