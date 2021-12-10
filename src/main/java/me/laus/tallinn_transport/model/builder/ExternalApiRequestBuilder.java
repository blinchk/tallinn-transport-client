package me.laus.tallinn_transport.model.builder;

import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.util.Map;

public class ExternalApiRequestBuilder {
    private final String host;
    private final String path;

    public ExternalApiRequestBuilder(String host, String path) {
        this.host = host;
        this.path = path;
    }
    
    private URI buildRequestUri(Map<String, String> parameters) throws URISyntaxException {
        URIBuilder endpointUriBuilder = new URIBuilder()
                .setScheme("https")
                .setHost(host)
                .setPath(path);
        if (parameters.isEmpty()) {
            return endpointUriBuilder.build();
        }
        for (Map.Entry parameter: parameters.entrySet()) {
            endpointUriBuilder.addParameter(String.valueOf(parameter.getKey()),
                    String.valueOf(parameter.getValue()));
        }
        return endpointUriBuilder.build();
    }

    private HttpRequest.Builder createBuilderForUri(Map<String, String> parameters) throws URISyntaxException {
        URI uri = buildRequestUri(parameters);
        System.out.println(uri.toString());
        return HttpRequest
                .newBuilder()
                .uri(uri);
    }

    public HttpRequest buildGetRequest(Map<String, String> parameters) {
        try {
            return createBuilderForUri(parameters).GET().build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
}
