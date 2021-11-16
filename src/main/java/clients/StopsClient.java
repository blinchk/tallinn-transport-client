package clients;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import models.SiriStop;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StopsClient {
    private final String path;

    public StopsClient(String path) {
        this.path = path;
    }

    public ArrayList<SiriStop> parse() {
        var stops = new ArrayList<SiriStop>();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
        CSVReader csvReader = new CSVReaderBuilder(fileReader).withCSVParser(csvParser).withSkipLines(1).build();
        List<String[]> result = null;
        try {
            result = csvReader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        List<String[]> stopsStrings = Objects.requireNonNull(result).stream().takeWhile(stop -> stop.length > 4).toList();
        String backupName = "";
        final int DEFAULT_NAME_INDEX = 5;
        for (var stopStrings : stopsStrings) {
            if (stopStrings.length >= SiriStop.MINIMUM_LENGTH_WITH_NAME) backupName = stopStrings[DEFAULT_NAME_INDEX];
            var stop = SiriStop.fromList(stopStrings, backupName);
            stops.add(stop);
        }
        return stops;
    }
}
