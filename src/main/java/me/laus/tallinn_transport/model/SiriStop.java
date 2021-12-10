package me.laus.tallinn_transport.model;

public class SiriStop extends Stop {
    private final String siriId;
    private final String[] otherIds;
    public final static int MINIMUM_LENGTH_WITH_NAME = 6;

    public SiriStop(String gtfsId, String name, Location location, String siriId, String[] otherIds) {
        super(gtfsId, location, name);
        this.siriId = siriId;
        this.otherIds = otherIds;
    }

    public String getSiriId() {
        return siriId;
    }
}
