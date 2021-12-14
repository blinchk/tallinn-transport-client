package me.laus.tallinn.transport.model.request;

import me.laus.tallinn.transport.model.SiriStop;

import java.time.Instant;
import java.util.List;

public abstract class SiriRequestParameters {
    public static List<RequestParameter> of(SiriStop stop) {
        String currentTime = String.valueOf(Instant.now().getEpochSecond());
        return List.of(new RequestParameter("stopid", stop.getSiriId()), new RequestParameter("time", currentTime));
    }
}
