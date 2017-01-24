package org.alancesar.darkroom.engine.exif;

public class CameraExif {

    private final String make;
    private final String model;

    public CameraExif(String make, String model) {
        this.make = make;
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

}
