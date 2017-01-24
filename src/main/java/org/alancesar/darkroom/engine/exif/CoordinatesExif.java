package org.alancesar.darkroom.engine.exif;

public class CoordinatesExif {

    private final double latitude;
    private final double longitude;

    public CoordinatesExif(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
