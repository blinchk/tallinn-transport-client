package client;

import model.Arrival;
import model.SiriStop;

import java.net.http.HttpRequest;
import java.time.Instant;
import java.util.*;

public class SiriClient extends ExternalApiClient {
    private static final String TRANSPORT_TALLINN_URL = "transport.tallinn.ee";
    private static final String SIRI_ENDPOINT = "/siri-stop-departures.php";
    private final SiriStop stop;

    private static Map<String, String> buildParameters(String stopId) {
        String currentTime = String.valueOf(Instant.now().getEpochSecond());
        return Map.of("stopid", stopId, "time", currentTime);
    }

    public SiriClient(SiriStop stop) {
        super(TRANSPORT_TALLINN_URL, SIRI_ENDPOINT, buildParameters(stop.getSiriId()));
        this.stop = stop;
    }

    public List<Arrival> requestArrivals() {
        HttpRequest request = buildGetRequest();
        String[] response = sendRequest(request);
        try {
            return Arrays
                    .asList(response)
                    .subList(2, response.length)
                    .stream()
                    .map(arrival -> arrival.split(","))
                    .map(arrival -> Arrival.fromList(arrival, stop))
                    .toList();
        } catch (IllegalArgumentException e) {
            return Collections.emptyList();
        }
    }
}
