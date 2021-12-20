package me.laus.tallinn.transport.client.external

import me.laus.tallinn.transport.exception.RequestBodyRequiredException
import me.laus.tallinn.transport.model.request.ExternalApiRequest
import me.laus.tallinn.transport.model.request.ExternalApiRequestScheme
import me.laus.tallinn.transport.model.request.HttpMethod
import java.io.IOException
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpRequest.BodyPublisher
import java.net.http.HttpResponse
import java.nio.charset.StandardCharsets
import java.util.*

open class ExternalApiClient(
    private val scheme: ExternalApiRequestScheme,
    private val host: String,
    private val path: String
) {
    private val defaultCharset = StandardCharsets.UTF_8
    private val responseBodyHandler = HttpResponse.BodyHandlers.ofString(defaultCharset)

    constructor(
        host: String,
        path: String
    ) : this(ExternalApiRequestScheme.HTTPS, host, path) {
    }

    protected fun sendRequest(request: HttpRequest?): Array<String> {
        val client = HttpClient.newHttpClient()
        lateinit var response: HttpResponse<String>
        try {
            response = client.send(request, responseBodyHandler)
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return Objects.requireNonNull(response)?.body()?.split("\n")?.toTypedArray()!!
    }

    fun buildRequest(method: HttpMethod): HttpRequest {
        if (method.isBodyRequired) throw RequestBodyRequiredException(method)
        return ExternalApiRequest.newBuilder().scheme(scheme).host(host).path(path)
            .method(method, HttpRequest.BodyPublishers.noBody()).build()!!
    }

    fun buildRequest(method: HttpMethod, parameters: List<ExternalApiRequest.Parameter?>?): HttpRequest {
        if (method.isBodyRequired) throw RequestBodyRequiredException(method)
        return buildRequest(method, HttpRequest.BodyPublishers.noBody(), parameters)
    }

    fun buildRequest(
        method: HttpMethod?,
        bodyPublisher: BodyPublisher?,
        parameters: List<ExternalApiRequest.Parameter?>?
    ): HttpRequest {
        return ExternalApiRequest.newBuilder().scheme(scheme).host(host).path(path).method(method!!, bodyPublisher)
            .parameters(parameters).build()!!
    }
}