package me.laus.tallinn.transport.client.external

import me.laus.tallinn.transport.model.realtime.Vehicle
import me.laus.tallinn.transport.model.request.HttpMethod


class RealTimeClient : TallinnExternalApiClient(GPS_ENDPOINT) {
    fun fetch(): List<Vehicle?> {
        val request = buildRequest(HttpMethod.GET)
        val response = sendRequest(request)
        return listOf(*response)
            .filter { position: String -> position.isNotEmpty() }
            .map { position: String -> position.split(",").toTypedArray() }
            .filter { parts: Array<String> -> parts.isNotEmpty() }
            .map { parts: Array<String>? -> parts?.let { Vehicle.Factory.fromList(it) } }
    }

    companion object {
        private const val GPS_ENDPOINT = "/gps.txt"
    }
}