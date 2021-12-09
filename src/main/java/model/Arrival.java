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

    private final static int TRANSPORT_TYPE_INDEX = 0;
    private final static int ROUTE_NUMBER_INDEX = 1;
    private final static int EXPECTED_TIME_IN_SECONDS_INDEX = 2;
    private final static int ACTUAL_TIME_IN_SECONDS_INDEX = 3;
    private final static int ROUTE_DESTINATION_INDEX = 4;

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
