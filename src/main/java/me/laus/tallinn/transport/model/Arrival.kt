package me.laus.tallinn.transport.model

import me.laus.tallinn.transport.model.siri.Route
import me.laus.tallinn.transport.util.DateUtils
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

class Arrival(
    private val route: Route,
    private val stop: Stop,
    private val actual: Date?,
    private val expected: Date?,
    val isLowFloor: Boolean
) {
    private val TIME_DIFFERENCE_MILLISECONDS_DELIMITER = 1000
    private val SECONDS_DELIMITER = 60

    constructor(route: Route, stop: Stop, expected: Date?, isLowFloor: Boolean) : this(
        route,
        stop,
        expected,
        expected,
        isLowFloor
    ) {
    }

    override fun toString(): String {
        val dateFormat = SimpleDateFormat("HH:mm")
        return String.format(
            "Stop: %s | Route: %s %s \n- Expected: %s | Actual: %s | %s min (%s min) \n- %s",
            stop.name,
            route.number,
            route.destination,
            dateFormat.format(expected),
            dateFormat.format(actual),
            remainingTimeInMinutes,
            timeDifferenceInMinutes,
            route.transportType.toString()
        )
    }

    val remainingTimeInSeconds: Long
        get() {
            val REMAINING_TIME_MILLISECONDS_DELIMITER = -TIME_DIFFERENCE_MILLISECONDS_DELIMITER
            return (Date.from(Instant.now()).time - actual!!.time) / REMAINING_TIME_MILLISECONDS_DELIMITER
        }
    val remainingTimeInMinutes: Long
        get() = (remainingTimeInSeconds / SECONDS_DELIMITER).toInt().toLong()
    val timeDifferenceInSeconds: Long
        get() = (actual!!.time - expected!!.time) / TIME_DIFFERENCE_MILLISECONDS_DELIMITER
    val timeDifferenceInMinutes: Long
        get() = (timeDifferenceInSeconds / SECONDS_DELIMITER).toInt().toLong()

    /**
     * Factory for [Arrival].
     */
    object Factory {
        private const val TRANSPORT_TYPE_INDEX = 0
        private const val ROUTE_NUMBER_INDEX = 1
        private const val EXPECTED_TIME_IN_SECONDS_INDEX = 2
        private const val ACTUAL_TIME_IN_SECONDS_INDEX = 3
        private const val ROUTE_DESTINATION_INDEX = 4
        fun fromList(parts: Array<String>, stop: Stop): Arrival {
            val transportType: TransportType = TransportType.Companion.fromSiriString(parts[TRANSPORT_TYPE_INDEX])
            val route = Route(transportType, parts[ROUTE_NUMBER_INDEX], parts[ROUTE_DESTINATION_INDEX])
            val actual = DateUtils.secondsToDate(parts[ACTUAL_TIME_IN_SECONDS_INDEX].toInt())
            val expected = DateUtils.secondsToDate(parts[EXPECTED_TIME_IN_SECONDS_INDEX].toInt())
            return Arrival(
                route,
                stop,
                actual,
                expected,
                parts.size > 6
            )
        }
    }
}