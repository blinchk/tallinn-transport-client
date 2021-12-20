package me.laus.tallinn.transport.model.gtfs


class Trip {
    var routeId: String? = null
    var serviceId: String? = null
    var id: Long? = null
    var headSign: String? = null
    var directionId = 0
    var blockId = 0
    var shapeId: String? = null
    var wheelchairAccessible = false
    var blockCode: String? = null
    var vehicleType: VehicleType? = null

    companion object {
        private const val ROUTE_ID_INDEX = 0
        private const val SERVICE_ID_INDEX = 1
        private const val TRIP_ID_INDEX = 2
        private const val HEAD_SIGN_INDEX = 3
        private const val DIRECTION_ID_INDEX = 4
        private const val BLOCK_ID_INDEX = 5
        private const val SHAPE_ID_INDEX = 6
        private const val WHEELCHAIR_ACCESSIBLE_INDEX = 7
        private const val BLOCK_CODE_INDEX = 8
        private const val VEHICLE_TYPE_INDEX = 9

        fun fromList(parts: Array<String>): Trip {
            return Trip().apply {
                routeId = parts[ROUTE_ID_INDEX]
                serviceId = parts[SERVICE_ID_INDEX]
                id = parts[TRIP_ID_INDEX].toLong()
                headSign = parts[HEAD_SIGN_INDEX]
                directionId = parts[DIRECTION_ID_INDEX].toInt()
                blockId = parts[BLOCK_ID_INDEX].toInt()
                shapeId = parts[SHAPE_ID_INDEX]
                wheelchairAccessible = parts[WHEELCHAIR_ACCESSIBLE_INDEX] == 1.toString()
                blockCode = parts[BLOCK_CODE_INDEX]
                vehicleType = VehicleType.valueOf(parts[VEHICLE_TYPE_INDEX])
            }
        }
    }
}