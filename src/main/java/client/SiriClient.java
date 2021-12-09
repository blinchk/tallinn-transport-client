package client;

import model.Arrival;
import model.SiriStop;
import model.request.SiriRequest;

import java.net.http.HttpRequest;
import java.util.*;

public class SiriClient extends ExternalApiClient {
    private static final String TRANSPORT_TALLINN_URL = "transport.tallinn.ee";
    private static final String SIRI_ENDPOINT = "/siri-stop-departures.php";

    public SiriClient() {
        super(TRANSPORT_TALLINN_URL, SIRI_ENDPOINT);
    }

    public List<Arrival> requestArrivals(SiriStop stop) {
        HttpRequest request = buildGetRequest(SiriRequest.buildParameters(stop));
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
