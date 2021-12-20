package me.laus.tallinn.transport

import me.laus.tallinn.transport.client.external.RealTimeClient
import me.laus.tallinn.transport.client.external.SiriClient
import me.laus.tallinn.transport.client.internal.SiriStopClient
import me.laus.tallinn.transport.model.Arrival
import me.laus.tallinn.transport.model.realtime.Vehicle
import me.laus.tallinn.transport.model.siri.Stop
import java.nio.file.Paths
import java.util.function.Consumer

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val currentPath = Paths.get("").toAbsolutePath().toString()
        val path = "$currentPath/src/main/resources/stops.txt"
        val stopsClient = SiriStopClient(path)
        val stops = stopsClient.parse()
        stops.forEach(Consumer { stop: Stop? -> println(stop?.siriId + " " + stop?.name + " " + stop?.location) })
        val stop = stops.stream().parallel().filter { _stop: Stop? -> _stop?.siriId == 870.toString() }
            .findFirst()
        val siriClient = SiriClient()
        siriClient.fetch(stop.orElseThrow { RuntimeException() }!!).forEach(
            Consumer { x: Arrival? -> println(x) })
        val realTimeClient = RealTimeClient()
        realTimeClient.fetch().forEach(Consumer { x: Vehicle? -> println(x) })
    }
}