package me.laus.tallinn.transport.client.external;

import me.laus.tallinn.transport.model.Arrival;
import me.laus.tallinn.transport.model.SiriStop;
import me.laus.tallinn.transport.model.request.RequestParameter;
import me.laus.tallinn.transport.model.request.SiriRequestParameters;
import me.laus.tallinn.transport.model.factory.ArrivalFactory;

import java.net.http.HttpRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SiriClient extends ExternalApiClient {
    private static final String TRANSPORT_TALLINN_URL = "transport.tallinn.ee";
    private static final String SIRI_ENDPOINT = "/siri-stop-departures.php";

    public SiriClient() {
        super(TRANSPORT_TALLINN_URL, SIRI_ENDPOINT);
    }

    public List<Arrival> request(SiriStop stop) {
        List<RequestParameter> parameters = SiriRequestParameters.of(stop);
        HttpRequest request = this.getRequestBuilder().buildGetRequest(parameters);
        String[] response = sendRequest(request);
        try {
            return Arrays
                    .asList(response)
                    .subList(2, response.length)
                    .stream()
                    .map(arrival -> arrival.split(","))
                    .map(arrival -> ArrivalFactory.fromList(arrival, stop))
                    .toList();
        } catch (IllegalArgumentException e) {
            return Collections.emptyList();
        }
    }
}
