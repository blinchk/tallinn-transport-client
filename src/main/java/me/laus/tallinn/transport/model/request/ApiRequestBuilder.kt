package me.laus.tallinn.transport.model.request

import org.apache.http.NameValuePair
import java.net.http.HttpRequest
import java.net.http.HttpRequest.BodyPublisher

interface ApiRequestBuilder {
    fun scheme(scheme: ExternalApiRequestScheme): ApiRequestBuilder
    fun https(): ApiRequestBuilder
    fun http(): ApiRequestBuilder
    fun host(host: String?): ApiRequestBuilder
    fun path(path: String?): ApiRequestBuilder
    fun parameters(parameters: List<NameValuePair?>?): ApiRequestBuilder
    fun method(method: HttpMethod, bodyPublisher: BodyPublisher?): ApiRequestBuilder
    fun build(): HttpRequest?
}