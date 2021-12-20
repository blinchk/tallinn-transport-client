package me.laus.tallinn.transport.model.realtime

import me.laus.tallinn.transport.model.Location
import me.laus.tallinn.transport.model.TransportType
import me.laus.tallinn.transport.util.LocationUtils

class Vehicle(val location: Location, val route: Route, val label: String, val number: String) {

    override fun toString(): String {
        return String.format(
            "%s %s | %f %f  | %s %s",
            route.transportType,
            route.number,
            location.latitude,
            location.longitude,
            label,
            number
        )
    }

    object Factory {
        fun fromList(parts: Array<String>): Vehicle {
            val transportTypeNumber = try {
                parts[0].toInt()
            } catch (e: NumberFormatException) {
                1
            }
            val transportType: TransportType = TransportType.fromGpsInteger(transportTypeNumber)
            val route = Route(transportType, parts[1])
            val latitude = parts[2].toDouble() / LocationUtils.GPS_COORDINATE_DELIMITER
            val longitude = parts[3].toDouble() / LocationUtils.GPS_COORDINATE_DELIMITER
            val location = Location(latitude, longitude)
            return Vehicle(location, route, parts[5], parts[6])
        }
    }
}