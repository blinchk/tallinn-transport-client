package me.laus.tallinn_transport.model.request;

import me.laus.tallinn_transport.model.SiriStop;
import me.laus.tallinn_transport.model.Stop;
import org.apache.http.NameValuePair;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public abstract class SiriRequestParameters implements List<RequestParameter> {
    public static List<RequestParameter> of(SiriStop stop) {
        String currentTime = String.valueOf(Instant.now().getEpochSecond());
        return List.of(new RequestParameter("stopid", stop.getSiriId()), new RequestParameter("time", currentTime));
    }
}
