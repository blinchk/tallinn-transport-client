package me.laus.tallinn.transport.model

enum class TransportType {
    TROLLEYBUS, BUS, TRAM, TRAIN, FERRY, SUBWAY, COMMERCIAL_BUS, REGIONAL_BUS;

    companion object {
        fun fromSiriString(type: String?): TransportType {
            return when (type) {
                "bus" -> BUS
                "trol" -> TROLLEYBUS
                "tram" -> TRAM
                "subway" -> SUBWAY
                "train" -> TRAIN
                "ferry" -> FERRY
                "regionalBus" -> REGIONAL_BUS
                "commercialBus" -> COMMERCIAL_BUS
                else -> throw IllegalArgumentException("Cannot be assigned to any kind of transport.")
            }
        }

        fun fromGpsInteger(type: Int): TransportType {
            return when (type) {
                1 -> TROLLEYBUS
                2 -> BUS
                3 -> TRAM
                else -> throw IllegalArgumentException("Cannot be assigned to any kind of transport.")
            }
        }
    }
}