package me.laus.tallinn.transport.client.internal

import me.laus.tallinn.transport.client.ParserClient
import me.laus.tallinn.transport.model.siri.Stop

class SiriStopClient(path: String?) : InternalCsvFileReaderClient(path), ParserClient<Stop?> {
    override fun parse(): ArrayList<Stop?> {
        val stops = ArrayList<Stop?>()
        val result = validateLength(readCsv()!!, 4)
        val defaultNameIndex = 5
        var backupName: String = ""
        for (stopStrings in result) {
            if (stopStrings.size >= Stop.MINIMUM_LENGTH_WITH_NAME) backupName =
                stopStrings[defaultNameIndex]
            val stop = Stop.Factory.fromList(stopStrings, backupName)
            stops.add(stop)
        }
        return stops
    }
}