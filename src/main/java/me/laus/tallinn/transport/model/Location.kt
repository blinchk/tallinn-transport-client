package me.laus.tallinn.transport.model

open class Location(val latitude: Double, val longitude: Double) {
    override fun toString(): String {
        return "$latitude, $longitude"
    }
}