package me.laus.tallinn.transport.util

import java.util.*

object DateUtils {
    fun secondsToDate(seconds: Int): Date {
        val now = Calendar.getInstance()
        val date = Date()
        now.time = date
        val hours = Integer.valueOf(seconds / 3600)
        val minutesSeconds = seconds % 3600
        now[Calendar.HOUR_OF_DAY] = hours
        now[Calendar.MINUTE] = minutesSeconds / 60
        now[Calendar.SECOND] = minutesSeconds % 60
        return Date.from(now.toInstant())
    }
}