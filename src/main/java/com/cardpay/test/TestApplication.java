package com.cardpay.test;

import com.cardpay.test.models.ParsingResult;
import com.cardpay.test.models.RawData;
import com.cardpay.test.models.ValidatedData;
import com.cardpay.test.parser.FileNotParsableException;
import com.cardpay.test.parser.Parser;
import com.cardpay.test.validators.RawDataValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.util.*;
import java.util.stream.Stream;

@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
