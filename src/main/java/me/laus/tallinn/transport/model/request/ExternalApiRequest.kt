package me.laus.tallinn.transport.model.request

import lombok.NoArgsConstructor
import org.apache.http.NameValuePair
import org.apache.http.client.utils.URIBuilder
import java.net.URI
import java.net.URISyntaxException
import java.net.http.HttpRequest
import java.net.http.HttpRequest.BodyPublisher
import java.util.*

@NoArgsConstructor
abstract class ExternalApiRequest {
    class Parameter(private val name: String, private val value: String) : NameValuePair {
        override fun getName(): String {
            return name
        }

        override fun getValue(): String {
            return value
        }
    }

    class ExternalApiRequestBuilder : ApiRequestBuilder {
        private val uriBuilder: URIBuilder
        private val httpRequestBuilder: HttpRequest.Builder

        init {
            uriBuilder = URIBuilder()
            httpRequestBuilder = HttpRequest.newBuilder()
        }

        override fun scheme(scheme: ExternalApiRequestScheme): ExternalApiRequestBuilder {
            uriBuilder.scheme = scheme.toString().lowercase(Locale.getDefault())
            return this
        }

        override fun https(): ExternalApiRequestBuilder {
            return scheme(ExternalApiRequestScheme.HTTPS)
        }

        override fun http(): ExternalApiRequestBuilder {
            return scheme(ExternalApiRequestScheme.HTTP)
        }

        override fun host(host: String?): ExternalApiRequestBuilder {
            uriBuilder.host = host
            return this
        }

        override fun path(path: String?): ExternalApiRequestBuilder {
            uriBuilder.path = path
            return this
        }

        override fun parameters(parameters: List<NameValuePair?>?): ExternalApiRequestBuilder {
            uriBuilder.addParameters(parameters)
            return this
        }

        override fun method(method: HttpMethod, bodyPublisher: BodyPublisher?): ExternalApiRequestBuilder {
            httpRequestBuilder.method(method.toString(), bodyPublisher)
            return this
        }

        private val uri: URI?
            get() {
                try {
                    return uriBuilder.build()
                } catch (e: URISyntaxException) {
                    e.printStackTrace()
                }
                return null
            }

        override fun build(): HttpRequest? {
            return httpRequestBuilder.uri(uri).build()
        }
    }

    companion object {
        fun newBuilder(): ExternalApiRequestBuilder {
            return ExternalApiRequestBuilder()
        }
    }
}