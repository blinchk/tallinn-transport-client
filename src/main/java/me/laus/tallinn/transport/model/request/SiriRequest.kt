package me.laus.tallinn.transport.model.request

import me.laus.tallinn.transport.model.siri.Stop
import java.time.Instant

abstract class SiriRequest : ExternalApiRequest() {
    companion object Parameters : ArrayList<Parameter?>() {
        fun buildParameters(stop: Stop): ArrayList<Parameter?> {
            return arrayListOf(stop.siriId?.let { Parameter("stopid", it) }, Parameter("time", Instant.EPOCH.toString()))
        }
    }
}