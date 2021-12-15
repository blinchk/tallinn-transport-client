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


    @NoArgsConstructor
    @AllArgsConstructor
    public static class Parameter implements NameValuePair {
        private String name;
        private String value;


        @Override
        public String getName() {
            return name;
        }

        void setName(String name) {
            this.name = name;
        }

        @Override
        public String getValue() {
            return value;
        }

        void setValue(String value) {
            this.value = value;
        }
    }

    public static class Builder {
        private final URIBuilder uriBuilder;
        private final HttpRequest.Builder httpRequestBuilder;

        public Builder() {
            this.uriBuilder = new URIBuilder();
            this.httpRequestBuilder = HttpRequest.newBuilder();
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
