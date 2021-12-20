package me.laus.tallinn.transport.model.siri

import me.laus.tallinn.transport.model.Location

class StopLocation(latitude: Double, longitude: Double) : Location(latitude, longitude) {
    companion object {
        const val LATITUDE_INDEX = 2
        const val LONGITUDE_INDEX = 3
    }
}