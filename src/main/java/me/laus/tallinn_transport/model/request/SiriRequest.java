package me.laus.tallinn_transport.model.request;

import me.laus.tallinn_transport.model.SiriStop;

import java.time.Instant;
import java.util.Map;

public class SiriRequest {
    public static Map<String, String> buildParameters(SiriStop stop) {
        String currentTime = String.valueOf(Instant.now().getEpochSecond());
        return Map.of("stopid", stop.getSiriId(), "time", currentTime);
    }
}
