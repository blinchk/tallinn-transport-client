package me.laus.tallinn.transport.client.external;

import me.laus.tallinn.transport.model.request.ExternalApiRequest;
import me.laus.tallinn.transport.model.request.ExternalApiRequestScheme;
import me.laus.tallinn.transport.model.request.HttpMethod;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

public class ExternalApiClient {
    private final String host;
    private final String path;
    private final ExternalApiRequestScheme scheme;

    private final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private final HttpResponse.BodyHandler<String> RESPONSE_BODY_HANDLER = HttpResponse.BodyHandlers.ofString(DEFAULT_CHARSET);

    public ExternalApiClient(ExternalApiRequestScheme scheme,
                             String host,
                             String path) {
        this.scheme = scheme;
        this.host = host;
        this.path = path;
    }

    public ExternalApiClient(String host,
                             String path) {
        this(ExternalApiRequestScheme.HTTPS, host, path);
    }

    protected String[] sendRequest(HttpRequest request) {
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, RESPONSE_BODY_HANDLER);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(response).body().split("\n");
    }

    public HttpRequest buildRequest(HttpMethod method, List<ExternalApiRequest.Parameter> parameters) {
        return buildRequest(method, HttpRequest.BodyPublishers.noBody(), parameters);
    }

    public HttpRequest buildRequest(HttpMethod method, HttpRequest.BodyPublisher bodyPublisher, List<ExternalApiRequest.Parameter> parameters) {
        return new ExternalApiRequest.Builder().setScheme(scheme).setHost(host).setPath(path).setMethod(method, bodyPublisher).addParameters(parameters).build();
    }
}
