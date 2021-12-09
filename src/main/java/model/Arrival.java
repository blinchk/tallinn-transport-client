package model;

import util.DateUtils;

import java.util.Date;
import java.util.Objects;

public class Arrival {
    private final Route route;
    private final Stop stop;
    private final Date actual;
    private final Date expected;
    private final boolean isLowFloor;

    public Arrival(Route route, Stop stop, Date actual, Date expected, boolean isLowFloor) {
        this.route = route;
        this.stop = stop;
        this.actual = actual;
        this.expected = expected;
        this.isLowFloor = isLowFloor;
    }

    public Arrival(Route route, Stop stop, Date expected, boolean isLowFloor) {
        this(route, stop, expected, expected, isLowFloor);
    }

    public String toString() {
        return String.format("Stop: %s | Route: %s %s | %s %s | %s",
                stop.getName(),
                route.getRouteNumber(),
                route.getDestination(),
                actual.toString(),
                expected.toString(),
                route.getTransportType().toString());
    }
}
