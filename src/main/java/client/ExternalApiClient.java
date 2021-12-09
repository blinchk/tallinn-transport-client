package client;

import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

public class ExternalApiClient {
    private final String host;
    private final String path;
    private Map<String, String> parameters = new HashMap<>();

    private final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private final HttpResponse.BodyHandler<String> RESPONSE_BODY_HANDLER = HttpResponse.BodyHandlers.ofString(DEFAULT_CHARSET);

    public ExternalApiClient(String host,
                             String path) {
        this.host = host;
        this.path = path;
    }

    public ExternalApiClient(String host,
                             String path,
                             Map<String, String> parameters) {
        this(host, path);
        this.parameters = parameters;
    }

    private URI getRequestUri() throws URISyntaxException {
        URIBuilder endpointUriBuilder = new URIBuilder()
                .setScheme("https")
                .setHost(this.host)
                .setPath(this.path);
        if (parameters.isEmpty()) {
            return endpointUriBuilder.build();
        }
        for (Entry parameter: parameters.entrySet()) {
            endpointUriBuilder.addParameter(String.valueOf(parameter.getKey()),
                    String.valueOf(parameter.getValue()));
        }
        return endpointUriBuilder.build();
    }

    private HttpRequest.Builder createBuilderForUri() throws URISyntaxException {
        URI uri = getRequestUri();
        System.out.println(uri.toString());
        return HttpRequest
                .newBuilder()
                .uri(uri);
    }

    protected HttpRequest buildGetRequest() {
        try {
            return createBuilderForUri().GET().build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
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
}
