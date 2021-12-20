package me.laus.tallinn.transport.model.realtime

import me.laus.tallinn.transport.model.Route
import me.laus.tallinn.transport.model.TransportType

open class Route(override val transportType: TransportType?, override val number: String?) : Route