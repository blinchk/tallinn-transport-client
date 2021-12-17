package me.laus.tallinn.transport.model;

public class Distance {
    private final double value;
    private final Unit unit;

    public Distance(double value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public Unit getUnit() {
        return unit;
    }

    public static Distance calculateBetweenPoints(Location source, Location destination) {
        final int earthRadius = 6371;

        double latitudeDistance = Math.toRadians(destination.getLatitude() - source.getLatitude());
        double longitudeDistance = Math.toRadians(destination.getLongitude() - source.getLongitude());
        double diagonal = Math.sqrt(Math.sin(latitudeDistance / 2))
                + Math.cos(Math.toRadians(source.getLatitude())) * Math.cos(destination.getLongitude())
                * Math.sqrt(Math.sin(longitudeDistance / 2));
        double distance = earthRadius * (2 * Math.atan2(Math.sqrt(diagonal), Math.sqrt(diagonal))) * 1000;
        return new Distance(Math.sqrt(distance), Unit.METERS);
    }
}
