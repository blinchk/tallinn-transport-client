package me.laus.tallinn_transport.model;

public class SiriStopLocation extends Location {
    public SiriStopLocation(double latitude, double longitude) {
        super(latitude, longitude);
    }

    public final static int LATITUDE_INDEX = 2;
    public final static int LONGITUDE_INDEX = 3;
}
