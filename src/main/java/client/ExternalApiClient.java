package client;

import model.builder.ExternalApiRequestBuilder;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class ExternalApiClient {
    private final String host;
    private final String path;

    private final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private final HttpResponse.BodyHandler<String> RESPONSE_BODY_HANDLER = HttpResponse.BodyHandlers.ofString(DEFAULT_CHARSET);

    public ExternalApiClient(String host,
                             String path) {
        this.host = host;
        this.path = path;
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

    public ExternalApiRequestBuilder getRequestBuilder() {
        return new ExternalApiRequestBuilder(this.host, this.path);
    }
}
