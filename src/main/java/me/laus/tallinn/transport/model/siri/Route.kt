package me.laus.tallinn.transport.model.siri

import me.laus.tallinn.transport.model.TransportType
import me.laus.tallinn.transport.model.realtime.Route

class Route(transportType: TransportType?, number: String?, val destination: String) : Route(transportType, number)