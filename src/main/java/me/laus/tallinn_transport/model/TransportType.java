package me.laus.tallinn_transport.model;

public enum TransportType {
    BUS,
    TRAM,
    TROLLEYBUS,
    TRAIN,
    FERRY,
    SUBWAY,
    COMMERCIAL_BUS,
    REGIONAL_BUS;

    public static TransportType fromString(String type) {
        return switch (type) {
            case "bus" -> TransportType.BUS;
            case "trol" -> TransportType.TROLLEYBUS;
            case "tram" -> TransportType.TRAM;
            case "subway" -> TransportType.SUBWAY;
            case "train" -> TransportType.TRAIN;
            case "ferry" -> TransportType.FERRY;
            case "regionalBus" -> TransportType.REGIONAL_BUS;
            case "commercialBus" -> TransportType.COMMERCIAL_BUS;
            default -> throw new IllegalArgumentException("Cannot be assigned to any kind of transport.");
        };
    }
}
