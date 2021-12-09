package model;

public enum TransportType {
    BUS("bus"),
    TRAM("tram"),
    TROLLEYBUS("trolleybus");

    private final String value;

    TransportType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TransportType fromString(String type) {
        return switch (type) {
            case "bus" -> TransportType.BUS;
            case "trolleybus" -> TransportType.TROLLEYBUS;
            case "tram" -> TransportType.TRAM;
            default -> throw new IllegalArgumentException("Isn't assignable to this type of array.");
        };
    }
}
