package me.laus.tallinn.transport.model;

public enum TransportType {
    TROLLEYBUS,
    BUS,
    TRAM,
    TRAIN,
    FERRY,
    SUBWAY,
    COMMERCIAL_BUS,
    REGIONAL_BUS;

    public static TransportType fromSiriString(String type) {
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

    public static TransportType fromGpsInteger(int type) {
        return switch (type) {
            case 1 -> TransportType.TROLLEYBUS;
            case 2 -> TransportType.BUS;
            case 3 -> TransportType.TRAM;
            default -> throw new IllegalArgumentException("Cannot be assigned to any kind of transport.");
        };
    }
}
