package model;

import java.time.Instant;
import java.util.Date;

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
        return String.format("Stop: %s | Route: %s %s \n- Expected: %s | Actual: %s | %s min (%s s) \n- %s",
                stop.getName(),
                route.getRouteNumber(),
                route.getDestination(),
                expected,
                actual,
                getRemainingTimeInMinutes(),
                getTimeDifferenceInSeconds(),
                route.getTransportType().toString());
    }

    public long getRemainingTimeInSeconds() {
        return Math.abs( Date.from(Instant.now()).getTime() - actual.getTime() ) / 1000;
    }

    public long getRemainingTimeInMinutes() {
        return (int) (getRemainingTimeInSeconds() / 60);
    }

    public long getTimeDifferenceInSeconds() {
        return ( actual.getTime() - expected.getTime() ) / 1000;
    }
}
