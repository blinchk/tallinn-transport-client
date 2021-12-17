package me.laus.tallinn.transport.client.external;

import me.laus.tallinn.transport.model.request.ExternalApiRequestScheme;

public abstract class TallinnExternalApiClient extends ExternalApiClient {
    private static final String TRANSPORT_TALLINN_URL = "transport.tallinn.ee";

    public TallinnExternalApiClient(String path) {
        super(TRANSPORT_TALLINN_URL, path);
    }
}
