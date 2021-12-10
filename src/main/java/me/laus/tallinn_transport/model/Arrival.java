package me.laus.tallinn_transport.model;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class Arrival {
    private final Route route;
    private final Stop stop;
    private final Date actual;
    private final Date expected;
    private final boolean isLowFloor;

    private final int TIME_DIFFERENCE_MILLISECONDS_DELIMITER = 1000;
    private final int REMAINING_TIME_MILLISECONDS_DELIMITER = -TIME_DIFFERENCE_MILLISECONDS_DELIMITER;
    private final int SECONDS_DELIMITER = 60;

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
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return String.format("Stop: %s | Route: %s %s \n- Expected: %s | Actual: %s | %s min (%s min) \n- %s",
                stop.getName(),
                route.getRouteNumber(),
                route.getDestination(),
                dateFormat.format(expected),
                dateFormat.format(actual),
                getRemainingTimeInMinutes(),
                getTimeDifferenceInMinutes(),
                route.getTransportType().toString());
    }

    public long getRemainingTimeInSeconds() {
        return ( Date.from(Instant.now()).getTime() - actual.getTime() ) / REMAINING_TIME_MILLISECONDS_DELIMITER;
    }

    public long getRemainingTimeInMinutes() {
        return (int) (getRemainingTimeInSeconds() / SECONDS_DELIMITER);
    }

    public long getTimeDifferenceInSeconds() {
        return ( actual.getTime() - expected.getTime() ) / TIME_DIFFERENCE_MILLISECONDS_DELIMITER;
    }
    public long getTimeDifferenceInMinutes() {
        return (int) (getTimeDifferenceInSeconds() / SECONDS_DELIMITER);
    }
}
