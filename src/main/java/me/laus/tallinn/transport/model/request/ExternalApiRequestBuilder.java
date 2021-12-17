package me.laus.tallinn.transport.model.request;

import lombok.NonNull;
import org.apache.http.NameValuePair;

import java.net.http.HttpRequest;
import java.util.List;

public interface ExternalApiRequestBuilder {
    ExternalApiRequestBuilder setScheme(ExternalApiRequestScheme scheme);
    ExternalApiRequestBuilder useHttps();
    ExternalApiRequestBuilder useHttp();
    ExternalApiRequestBuilder setHost(String host);
    ExternalApiRequestBuilder setPath(String path);
    ExternalApiRequestBuilder addParameters(List<? extends NameValuePair> parameters);
    ExternalApiRequestBuilder setMethod(@NonNull HttpMethod method, HttpRequest.BodyPublisher bodyPublisher);
    HttpRequest build();
}
