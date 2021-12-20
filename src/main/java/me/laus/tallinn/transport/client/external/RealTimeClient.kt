package me.laus.tallinn.transport.client.external

import me.laus.tallinn.transport.model.realtime.Vehicle
import me.laus.tallinn.transport.model.request.HttpMethod
import java.util.*

class RealTimeClient : TallinnExternalApiClient(GPS_ENDPOINT) {
    fun fetch(): List<Vehicle> {
        val request = buildRequest(HttpMethod.GET)
        val response = sendRequest(request)
        return try {
            Arrays
                .stream(response)
                .map { position: String? -> position!!.split(",").toTypedArray() }
                .map { parts: Array<String>? -> parts?.let { Vehicle.Factory.fromList(it) } }
                .toList()
        } catch (e: IllegalArgumentException) {
            emptyList()
        }
    }

    companion object {
        private const val GPS_ENDPOINT = "/gps.txt"
    }
}