package me.laus.tallinn.transport.model

import lombok.Getter
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

@Getter
class Distance(val value: Double, val unit: Unit) {
    companion object {
        fun calculateBetweenPoints(source: Location, destination: Location): Distance {
            val earthRadius = 6371
            val latitudeDistance = Math.toRadians(destination.latitude - source.latitude)
            val longitudeDistance = Math.toRadians(destination.longitude - source.longitude)
            val diagonal = (sqrt(sin(latitudeDistance / 2))
                    + (cos(Math.toRadians(source.latitude)) * cos(destination.longitude)
                    * sqrt(sin(longitudeDistance / 2))))
            val distance = earthRadius * (2 * atan2(sqrt(diagonal), sqrt(diagonal))) * 1000
            return Distance(sqrt(distance), Unit.METERS)
        }
    }
}