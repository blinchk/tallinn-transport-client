package me.laus.tallinn_transport.client.internal;

import me.laus.tallinn_transport.model.SiriStop;
import me.laus.tallinn_transport.model.factory.SiriStopFactory;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SiriStopClient extends InternalCsvFileClient implements CsvParserClient<SiriStop> {
    private final String path;

    public SiriStopClient(String path) {
        this.path = path;
    }

    public ArrayList<SiriStop> parse() {
        var stops = new ArrayList<SiriStop>();
        FileReader fileReader = readFile(path);
        List<String[]> result = readCsv(fileReader);
        List<String[]> stopsStrings = Objects.requireNonNull(result).stream().takeWhile(stop -> stop.length > 4).toList();
        String backupName = "";
        final int DEFAULT_NAME_INDEX = 5;
        for (var stopStrings : stopsStrings) {
            if (stopStrings.length >= SiriStop.MINIMUM_LENGTH_WITH_NAME) backupName = stopStrings[DEFAULT_NAME_INDEX];
            var stop = SiriStopFactory.fromList(stopStrings, backupName);
            stops.add(stop);
        }
        return stops;
    }
}
