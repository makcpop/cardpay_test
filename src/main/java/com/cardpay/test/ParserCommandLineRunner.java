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
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;
import java.util.stream.Stream;

@Component
public class ParserCommandLineRunner implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(TestApplication.class);

    private final List<Parser> parsers;
    private final RawDataValidator rawDataValidator;
    private final ObjectMapper objectMapper;

    public ParserCommandLineRunner(List<Parser> parsers,
                                   RawDataValidator rawDataValidator,
                                   ObjectMapper objectMapper) {
        this.parsers = parsers;
        this.rawDataValidator = rawDataValidator;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(args)
                .parallel()
                .flatMap(this::parse)
                .map(this::validate)
                .forEach(this::print);
    }

    private ValidatedData validate(RawData rawData) {
        List<ParsingResult> parsingResults = new ArrayList<>();
        rawDataValidator.validate(rawData, parsingResults);

        ValidatedData.Builder validatedDataBuilder = ValidatedData.builder()
                .fileName(rawData.getFileName())
                .line(rawData.getLine());

        if (parsingResults.isEmpty()) {
            validatedDataBuilder = validatedDataBuilder
                    .id(Long.parseLong(rawData.getId()))
                    .amount(Long.parseLong(rawData.getAmount()))
                    .currency(Currency.getInstance(rawData.getCurrency()))
                    .comment(rawData.getComment())
                    .result(Collections.singletonList(ParsingResult.OK));
        } else {
            validatedDataBuilder = validatedDataBuilder
                    .result(parsingResults);
        }
        return validatedDataBuilder.build();
    }

    private void print(ValidatedData data) {
        try {
            System.out.println(objectMapper.writeValueAsString(data));
        } catch (JsonProcessingException e) {
            LOG.error("Can't serialize json", e);
        }
    }

    private Stream<RawData> parse(String filePath) {
        if (!new File(filePath).exists()) {
            printError(filePath, ParsingResult.FILE_NOT_EXIST);
            return Stream.empty();
        }

        for (Parser parser : parsers) {
            if (parser.isApplicable(filePath)) {
                return parseFile(filePath, parser);
            }
        }

        printError(filePath, ParsingResult.UNSUPPORTED_FILE_FORMAT);
        return Stream.empty();
    }

    private Stream<RawData> parseFile(String filePath, Parser parser) {
        try {
            return parser.parse(filePath).stream();
        } catch (FileNotParsableException e) {
            printError(filePath, ParsingResult.FILE_NOT_PARSABLE);
            return Stream.empty();
        }
    }

    private void printError(String filePath, ParsingResult parsingResult) {
        print(ValidatedData.builder()
                .fileName(filePath)
                .result(Collections.singletonList(parsingResult))
                .build());
    }
}
