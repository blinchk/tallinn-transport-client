package me.laus.tallinn_transport.model.factory;

import me.laus.tallinn_transport.model.Location;
import me.laus.tallinn_transport.model.SiriStop;
import me.laus.tallinn_transport.model.SiriStopLocation;

import static me.laus.tallinn_transport.model.SiriStop.MINIMUM_LENGTH_WITH_NAME;

public class SiriStopFactory {
    private final static int GTFS_ID_INDEX = 0;
    private final static int SIRI_ID_INDEX = 1;
    private final static int NAME_INDEX = 5;
    private final static int OTHER_IDS_INDEX = 4;
    private final static int COORDINATE_DENOMINATOR = 100000;

    public static SiriStop fromList(String[] parts, String backupName) {
        String gtfsId = parts[GTFS_ID_INDEX];
        String name = parts.length >= MINIMUM_LENGTH_WITH_NAME ? parts[NAME_INDEX] : backupName;
        double latitude = Double.parseDouble(parts[SiriStopLocation.LATITUDE_INDEX]) / COORDINATE_DENOMINATOR;
        double longitude = Double.parseDouble(parts[SiriStopLocation.LONGITUDE_INDEX]) / COORDINATE_DENOMINATOR;
        String siriId = parts[SIRI_ID_INDEX];
        String[] otherIds = parts[OTHER_IDS_INDEX].split(",");
        Location location = new SiriStopLocation(latitude, longitude);
        return
                new SiriStop(gtfsId,
                        name,
                        location,
                        siriId,
                        otherIds
                );
    }
}
