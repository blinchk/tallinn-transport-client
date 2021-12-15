package me.laus.tallinn.transport.model;

import me.laus.tallinn.transport.util.DateUtils;

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
        final int REMAINING_TIME_MILLISECONDS_DELIMITER = -TIME_DIFFERENCE_MILLISECONDS_DELIMITER;
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

    public static class Factory {
        private final static int TRANSPORT_TYPE_INDEX = 0;
        private final static int ROUTE_NUMBER_INDEX = 1;
        private final static int EXPECTED_TIME_IN_SECONDS_INDEX = 2;
        private final static int ACTUAL_TIME_IN_SECONDS_INDEX = 3;
        private final static int ROUTE_DESTINATION_INDEX = 4;

        public static Arrival fromList(String[] parts, Stop stop) {
            TransportType transportType = TransportType.fromString(parts[TRANSPORT_TYPE_INDEX]);
            Route route = new Route(transportType, parts[ROUTE_NUMBER_INDEX], parts[ROUTE_DESTINATION_INDEX]);
            Date actual = DateUtils.secondsToDate(Integer.parseInt(parts[ACTUAL_TIME_IN_SECONDS_INDEX]));
            Date expected = DateUtils.secondsToDate(Integer.parseInt(parts[EXPECTED_TIME_IN_SECONDS_INDEX]));
            return new Arrival(route,
                    stop,
                    actual,
                    expected,
                    parts.length > 6
            );
        }
    }
}
