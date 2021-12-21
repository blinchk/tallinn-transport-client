package me.laus.tallinn.transport.client.internal

import me.laus.tallinn.transport.client.ParserClient
import me.laus.tallinn.transport.model.gtfs.Trip

class TripClient(path: String) : InternalCsvFileReaderClient(path), ParserClient<Trip?> {
    override fun parse(): ArrayList<Trip?> {
        val trips = ArrayList<Trip?>()
        val result = validateLength(readCsv()!!, 9)
        for (tripStrings in result) {
            val trip = Trip.fromList(tripStrings)
            trips.add(trip)
        }
        return trips
    }
}