package me.laus.tallinn_transport.util;

import java.nio.file.Paths;

public class FileSystemUtils {
    private final static String CURRENT_PATH = Paths.get("").toAbsolutePath().toString();

    public final static String DEFAULT_STOPS_PATH = CURRENT_PATH + "/src/main/resources/stops.txt";
}
