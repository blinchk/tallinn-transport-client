package me.laus.tallinn.transport.client.internal;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class InternalCsvFileReaderClient extends InternalFileReaderClient {
    public List<String[]> readCsv(FileReader fileReader) {
        CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
        CSVReader csvReader = new CSVReaderBuilder(fileReader).withCSVParser(csvParser).withSkipLines(1).build();
        try {
            return csvReader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return null;
    }
}
