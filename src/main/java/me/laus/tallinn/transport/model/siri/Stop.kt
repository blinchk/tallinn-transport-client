package me.laus.tallinn.transport.model.siri;

import me.laus.tallinn.transport.model.Location;

import static me.laus.tallinn.transport.util.LocationUtils.SIRI_COORDINATE_DELIMITER;

public class Stop extends me.laus.tallinn.transport.model.Stop {
    private final String siriId;
    private final String[] otherIds;
    public final static int MINIMUM_LENGTH_WITH_NAME = 6;

    public Stop(String gtfsId, String name, Location location, String siriId, String[] otherIds) {
        super(gtfsId, location, name);
        this.siriId = siriId;
        this.otherIds = otherIds;
    }

    public String getSiriId() {
        return siriId;
    }

    public static class Factory {
        private final static int GTFS_ID_INDEX = 0;
        private final static int SIRI_ID_INDEX = 1;
        private final static int NAME_INDEX = 5;
        private final static int OTHER_IDS_INDEX = 4;

        public static Stop fromList(String[] parts, String backupName) {
            String gtfsId = parts[GTFS_ID_INDEX];
            String name = parts.length >= Stop.MINIMUM_LENGTH_WITH_NAME ? parts[NAME_INDEX] : backupName;
            double latitude = Double.parseDouble(parts[StopLocation.LATITUDE_INDEX]) / SIRI_COORDINATE_DELIMITER;
            double longitude = Double.parseDouble(parts[StopLocation.LONGITUDE_INDEX]) / SIRI_COORDINATE_DELIMITER;
            String siriId = parts[SIRI_ID_INDEX];
            String[] otherIds = parts[OTHER_IDS_INDEX].split(",");
            Location location = new StopLocation(latitude, longitude);
            return
                    new Stop(gtfsId,
                            name,
                            location,
                            siriId,
                            otherIds
                    );
        }
    }

}
