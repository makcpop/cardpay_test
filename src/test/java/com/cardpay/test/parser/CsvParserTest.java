package com.cardpay.test.parser;

import com.cardpay.test.models.RawData;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

class CsvParserTest {

    private final CsvParser csvParser = new CsvParser();

    @Test
    public void testCorrectFileParsing() throws FileNotParsableException{
        String file = this.getClass().getResource("correct_csv_file.csv").getFile();
        Collection<RawData> parse = csvParser.parse(file);
        assertThat(new ArrayList<>(parse), contains(RawData.builder()
                .id("1")
                .amount("100")
                .currency("USD")
                .comment("comment")
                .fileName(file)
                .line(1)
                .build()));
    }
}