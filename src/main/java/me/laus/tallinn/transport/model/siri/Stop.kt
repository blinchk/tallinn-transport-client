package me.laus.tallinn.transport.model.siri

import me.laus.tallinn.transport.model.Location
import me.laus.tallinn.transport.util.LocationUtils

class Stop(
    gtfsId: String?,
    name: String?,
    location: Location?,
    val siriId: String?
) : me.laus.tallinn.transport.model.Stop(gtfsId, location, name) {
    object Factory {
        private const val GTFS_ID_INDEX = 0
        private const val SIRI_ID_INDEX = 1
        private const val NAME_INDEX = 5
        private const val OTHER_IDS_INDEX = 4
        fun fromList(parts: Array<String>, backupName: String?): Stop {
            val gtfsId = parts[GTFS_ID_INDEX]
            val name = if (parts.size >= MINIMUM_LENGTH_WITH_NAME) parts[NAME_INDEX] else backupName
            val latitude =
                parts[StopLocation.LATITUDE_INDEX].toDouble() / LocationUtils.SIRI_COORDINATE_DELIMITER
            val longitude =
                parts[StopLocation.LONGITUDE_INDEX].toDouble() / LocationUtils.SIRI_COORDINATE_DELIMITER
            val siriId = parts[SIRI_ID_INDEX]
            val location: Location = StopLocation(latitude, longitude)
            return Stop(
                gtfsId,
                name,
                location,
                siriId
            )
        }
    }

    companion object {
        const val MINIMUM_LENGTH_WITH_NAME = 6
    }
}