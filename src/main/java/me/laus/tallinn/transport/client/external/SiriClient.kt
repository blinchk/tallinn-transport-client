package me.laus.tallinn.transport.client.external

import me.laus.tallinn.transport.model.Arrival
import me.laus.tallinn.transport.model.request.ExternalApiRequest
import me.laus.tallinn.transport.model.request.HttpMethod
import me.laus.tallinn.transport.model.request.SiriRequest
import me.laus.tallinn.transport.model.siri.Stop

class SiriClient : TallinnExternalApiClient(SIRI_ENDPOINT) {
    fun fetch(stop: Stop): List<Arrival> {
        val parameters: List<ExternalApiRequest.Parameter?> = SiriRequest.buildParameters(stop)
        val request = buildRequest(HttpMethod.GET, parameters)
        val response = sendRequest(request)
        return try {
            listOf(*response)
                .subList(2, response.size)
                .stream()
                .map { arrival: String? -> arrival!!.split(",").toTypedArray() }
                .map { arrival: Array<String>? -> arrival?.let { Arrival.Factory.fromList(it, stop) } }
                .toList()
        } catch (e: IllegalArgumentException) {
            emptyList()
        }
    }

    companion object {
        private const val SIRI_ENDPOINT = "/siri-stop-departures.php"
    }
}