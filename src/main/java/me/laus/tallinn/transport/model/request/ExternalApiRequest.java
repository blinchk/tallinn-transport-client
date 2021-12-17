package me.laus.tallinn.transport.model.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.util.List;

@NoArgsConstructor
public abstract class ExternalApiRequest {
    public static Builder newBuilder() {
        return new ExternalApiRequest.Builder();
    }

    public static class Parameter implements NameValuePair {
        private final String name;
        private final String value;

        public Parameter(String name, String value) {
            this.name = name;
            this.value = value;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getValue() {
            return value;
        }
    }

    public static class Builder implements ExternalApiRequestBuilder {
        private final URIBuilder uriBuilder;
        private final HttpRequest.Builder httpRequestBuilder;

        public Builder() {
            this.uriBuilder = new URIBuilder();
            this.httpRequestBuilder = HttpRequest.newBuilder();
        }

        public Builder setScheme(ExternalApiRequestScheme scheme) {
            this.uriBuilder.setScheme(scheme.toString().toLowerCase());
            return this;
        }

        public Builder useHttps() {
            return setScheme(ExternalApiRequestScheme.HTTPS);
        }

        public Builder useHttp() {
            return setScheme(ExternalApiRequestScheme.HTTP);
        }

        public Builder setHost(String host) {
            this.uriBuilder.setHost(host);
            return this;
        }

        public Builder setPath(String path) {
            this.uriBuilder.setPath(path);
            return this;
        }

        public Builder addParameters(List<? extends NameValuePair> parameters) {
            this.uriBuilder.addParameters((List<NameValuePair>) parameters);
            return this;
        }

        public Builder setMethod(@NonNull HttpMethod method, HttpRequest.BodyPublisher bodyPublisher) {
            this.httpRequestBuilder.method(method.toString(), bodyPublisher);
            return this;
        }

        private URI getUri() {
            try {
                return this.uriBuilder.build();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            return null;
        }

        public HttpRequest build() {
            return this.httpRequestBuilder.uri(getUri()).build();
        }
    }
}
