package me.laus.tallinn.transport.model

interface Route {
    val transportType: TransportType?
    val number: String?
}