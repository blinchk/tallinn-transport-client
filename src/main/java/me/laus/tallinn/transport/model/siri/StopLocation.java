package me.laus.tallinn.transport.model.siri;

import me.laus.tallinn.transport.model.Location;

public class StopLocation extends Location {
    public StopLocation(double latitude, double longitude) {
        super(latitude, longitude);
    }

    public final static int LATITUDE_INDEX = 2;
    public final static int LONGITUDE_INDEX = 3;
}
