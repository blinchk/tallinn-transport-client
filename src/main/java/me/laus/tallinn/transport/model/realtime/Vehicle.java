package me.laus.tallinn.transport.model.realtime;

import me.laus.tallinn.transport.model.Location;
import me.laus.tallinn.transport.model.TransportType;

import static me.laus.tallinn.transport.util.LocationUtils.GPS_COORDINATE_DELIMITER;

public class Vehicle {
    private final Location location;
    private final Route route;
    private final String label;
    private final String number;

    public Vehicle(Location location, Route route, String label, String number) {
        this.location = location;
        this.route = route;
        this.label = label;
        this.number = number;
    }

    public Location getLocation() {
        return location;
    }

    public Route getRoute() {
        return route;
    }

    public String toString() {
        return String.format("%s %s | %f %f  | %s %s",
                getRoute().getTransportType(),
                getRoute().getNumber(),
                getLocation().getLatitude(),
                getLocation().getLongitude(),
                getLabel(),
                getNumber());
    }

    public String getLabel() {
        return label;
    }

    public String getNumber() {
        return number;
    }

    public static class Factory {
        public static Vehicle fromList(String[] parts) {
            TransportType transportType = TransportType.fromGpsInteger(Integer.parseInt(parts[0]));
            Route route = new Route(transportType, parts[1]);
            double latitude = Double.parseDouble(parts[2]) / GPS_COORDINATE_DELIMITER;
            double longitude = Double.parseDouble(parts[3]) / GPS_COORDINATE_DELIMITER;
            Location location = new Location(latitude, longitude);
            return new Vehicle(location, route, parts[5], parts[6]);
        }
    }
}
