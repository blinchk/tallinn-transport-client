package me.laus.tallinn.transport.model.realtime;

import me.laus.tallinn.transport.model.TransportType;

public class Route implements me.laus.tallinn.transport.model.Route {
    private final TransportType transportType;
    private final String number;

    public Route(TransportType transportType, String number) {
        this.transportType = transportType;
        this.number = number;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public String getNumber() {
        return number;
    }
}
