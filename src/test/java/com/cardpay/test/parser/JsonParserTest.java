package com.cardpay.test.parser;

import com.cardpay.test.models.RawData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.fail;

class JsonParserTest {

    private final JsonParser jsonParser = new JsonParser(new ObjectMapper());

    @Test
    public void testParserCorrectFile() throws FileNotParsableException {
        String file = this.getClass().getResource("correct_json_file.json").getFile();
        Collection<RawData> parse = jsonParser.parse(file);
        assertThat(new ArrayList<>(parse), contains(RawData.builder()
                .id("1")
                .amount("100")
                .currency("USD")
                .comment("comment")
                .fileName(file)
                .line(1)
                .build()));
    }

    @Test
    public void testParserIncorrectFile() throws FileNotParsableException {
        try{
            String file = this.getClass().getResource("incorrect_json_file.json").getFile();
            jsonParser.parse(file);
            fail();
        } catch (FileNotParsableException e) {
            // correct result
        }
    }
}