package models;

public class SiriStop extends Stop {
    private final String siriId;
    private String[] otherIds;

    public final static int GTFS_ID_INDEX = 0;
    public final static int SIRI_ID_INDEX = 1;
    public final static int NAME_INDEX = 5;
    public final static int OTHER_IDS_INDEX = 4;

    public final static int MINIMUM_LENGTH_WITH_NAME = 6;

    public SiriStop(String gtfsId, String name, Location location, String siriId) {
        super(gtfsId, location, name);
        this.siriId = siriId;
    }

    public SiriStop(String gtfsId, String name, Location location, String siriId, String[] otherIds) {
        super(gtfsId, location, name);
        this.siriId = siriId;
        this.otherIds = otherIds;
    }

    public static SiriStop fromList(String[] parts, String backupName) {
        String gtfsId = parts[GTFS_ID_INDEX];
        String name = parts.length >= MINIMUM_LENGTH_WITH_NAME ? parts[NAME_INDEX] : backupName;
        double latitude = Double.parseDouble(parts[SiriStopLocation.LATITUDE_INDEX]) / 100000;
        double longitude = Double.parseDouble(parts[SiriStopLocation.LONGITUDE_INDEX]) / 10000;
        String siriId = parts[SIRI_ID_INDEX];
        String[] otherIds = parts[OTHER_IDS_INDEX].split(",");
        Location location = new Location(latitude, longitude);
        return
                new SiriStop(gtfsId,
                        name,
                        location,
                        siriId,
                        otherIds
                );
    }
}
