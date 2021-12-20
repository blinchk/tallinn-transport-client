package me.laus.tallinn.transport.client.external

abstract class TallinnExternalApiClient(path: String) : ExternalApiClient(TRANSPORT_TALLINN_URL, path) {
    companion object {
        private const val TRANSPORT_TALLINN_URL = "transport.tallinn.ee"
    }
}