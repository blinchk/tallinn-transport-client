package me.laus.tallinn_transport.model.builder;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.util.List;

public class ExternalApiRequestBuilder {
    private final String host;
    private final String path;

    public ExternalApiRequestBuilder(String host, String path) {
        this.host = host;
        this.path = path;
    }

    private URI buildRequestUri(List<? extends NameValuePair> parameters) throws URISyntaxException {
        return new URIBuilder()
                .setScheme("https")
                .setHost(host)
                .setPath(path)
                .addParameters((List<NameValuePair>) parameters)
                .build();
    }

    private HttpRequest.Builder createBuilderForUri(List<? extends NameValuePair> parameters) throws URISyntaxException {
        URI uri = buildRequestUri(parameters);
        System.out.println(uri.toString());
        return HttpRequest
                .newBuilder()
                .uri(uri);
    }

    public HttpRequest buildGetRequest(List<? extends NameValuePair> parameters) {
        try {
            return createBuilderForUri(parameters).GET().build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
}
