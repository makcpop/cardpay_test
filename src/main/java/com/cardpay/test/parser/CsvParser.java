package com.cardpay.test.parser;

import com.cardpay.test.models.RawData;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Csv files parser
 */
@Service
public class CsvParser implements Parser {

    @Override
    public boolean isApplicable(String fileName) {
        return fileName.endsWith(".csv");
    }

    @Override
    public Collection<RawData> parse(String fileName) throws FileNotParsableException{
        try {
            CsvToBean<DataLine> csvToBean = new CsvToBean<>();
            CSVReader csvReader = new CSVReaderBuilder(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8))
                    .build();
            csvToBean.setCsvReader(csvReader);
            csvToBean.setMappingStrategy(columnMapping());
            return convert(csvToBean.parse(), fileName);
        } catch (Exception e) {
            throw new FileNotParsableException(e);
        }
    }

    private List<RawData> convert(List<DataLine> dataLines, String fileName) {
        List<RawData> result = new ArrayList<>();
        for (int i = 0; i < dataLines.size(); i++) {
            DataLine dataLine = dataLines.get(i);
            result.add(RawData.builder()
                    .id(dataLine.getOrderId())
                    .amount(dataLine.getAmount())
                    .currency(dataLine.getCurrency())
                    .comment(dataLine.getComment())
                    .fileName(fileName)
                    .line(i + 1)
                    .build());
        }
        return result;
    }

    private static ColumnPositionMappingStrategy<DataLine> columnMapping() {
        ColumnPositionMappingStrategy<DataLine> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(DataLine.class);
        String[] columns = new String[] {"orderId", "amount", "currency", "comment"};
        strategy.setColumnMapping(columns);
        return strategy;
    }
}
