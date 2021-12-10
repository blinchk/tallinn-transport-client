package me.laus.tallinn_transport.model;

public class Route {
    private final TransportType transportType;
    private final String routeNumber;
    private final String destination;

    public Route(TransportType transportType, String routeNumber, String destination) {
        this.transportType = transportType;
        this.routeNumber = routeNumber;
        this.destination = destination;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public String getRouteNumber() {
        return routeNumber;
    }

    public String getDestination() {
        return destination;
    }
}
