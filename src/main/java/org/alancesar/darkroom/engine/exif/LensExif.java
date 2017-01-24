package org.alancesar.darkroom.engine.exif;

public class LensExif {

    private final String make;
    private final String model;
    
    public LensExif(String make, String model) {
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
