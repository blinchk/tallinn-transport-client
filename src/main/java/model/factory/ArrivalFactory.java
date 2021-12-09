package model.factory;

import model.Arrival;
import model.Route;
import model.Stop;
import model.TransportType;
import util.DateUtils;

import java.util.Date;

public class ArrivalFactory {
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
