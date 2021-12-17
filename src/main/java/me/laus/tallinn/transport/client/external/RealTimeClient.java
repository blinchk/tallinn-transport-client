package me.laus.tallinn.transport.client.external;

import me.laus.tallinn.transport.model.realtime.Vehicle;
import me.laus.tallinn.transport.model.request.HttpMethod;

import java.net.http.HttpRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RealTimeClient extends TallinnExternalApiClient {
    private static final String GPS_ENDPOINT = "/gps.txt";

    public RealTimeClient() {
        super(GPS_ENDPOINT);
    }

    public List<Vehicle> fetch() {
        HttpRequest request = buildRequest(HttpMethod.GET);
        String[] response = sendRequest(request);
        try {
            return Arrays
                    .stream(response)
                    .map(position -> position.split(","))
                    .map(Vehicle.Factory::fromList)
                    .toList();
        } catch (IllegalArgumentException e) {
            return Collections.emptyList();
        }
    }
}
