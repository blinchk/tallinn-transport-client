package me.laus.tallinn.transport.model;

public class Stop {
    private final String gtfsId;
    private final Location location;
    private final String name;
    private String address;

    public Stop(String gtfsId, Location location, String name) {
        this.gtfsId = gtfsId;
        this.location = location;
        this.name = name;
    }

    public int getHashCode() {
        return this.gtfsId.hashCode();
    }

    public Stop(String gtfsId, Location location, String name, String address) {
        this(gtfsId, location, name);
        this.address = address;
    }

    public String getGtfsId() {
        return gtfsId;
    }

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
