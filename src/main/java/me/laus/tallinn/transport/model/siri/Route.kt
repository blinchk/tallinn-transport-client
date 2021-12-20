package me.laus.tallinn.transport.model.siri;

import me.laus.tallinn.transport.model.TransportType;

public class Route extends me.laus.tallinn.transport.model.realtime.Route {
    private final String destination;

    public Route(TransportType transportType, String number, String destination) {
        super(transportType, number);
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }
}
