package me.laus.tallinn.transport.client.internal;

import me.laus.tallinn.transport.client.ParserClient;
import me.laus.tallinn.transport.model.siri.Stop;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SiriStopClient extends InternalCsvFileReaderClient implements ParserClient<Stop> {
    private final String path;

    public SiriStopClient(String path) {
        this.path = path;
    }

    public ArrayList<Stop> parse() {
        var stops = new ArrayList<Stop>();
        FileReader fileReader = readFile(path);
        List<String[]> result = readCsv(fileReader);
        List<String[]> stopsStrings = Objects.requireNonNull(result).stream().takeWhile(stop -> stop.length > 4).toList();
        String backupName = "";
        final int DEFAULT_NAME_INDEX = 5;
        for (var stopStrings : stopsStrings) {
            if (stopStrings.length >= Stop.MINIMUM_LENGTH_WITH_NAME) backupName = stopStrings[DEFAULT_NAME_INDEX];
            var stop = Stop.Factory.fromList(stopStrings, backupName);
            stops.add(stop);
        }
        return stops;
    }
}
