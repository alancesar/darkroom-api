package org.alancesar.darkroom.engine.exif;

public class ExifBuilder {

    private CameraExif camera;
    private LensExif lens;
    private CoordinatesExif coordinates;
    private String focalLength;
    private String focalLength35mm;
    private String exposureTime;
    private String aperture;
    private String iso;
    private String flash;
    private String whiteBalance;

    public ExifBuilder camera(CameraExif camera) {
        this.camera = camera;
        return this;
    }

    public ExifBuilder lens(LensExif lens) {
        this.lens = lens;
        return this;
    }
    
    public ExifBuilder coordinates(CoordinatesExif coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    public ExifBuilder focalLength(String focalLength) {
        this.focalLength = focalLength;
        return this;
    }

    public ExifBuilder focalLength35mm(String focalLength35mm) {
        this.focalLength35mm = focalLength35mm;
        return this;
    }

    public ExifBuilder exposureTime(String exposureTime) {
        this.exposureTime = exposureTime;
        return this;
    }

    public ExifBuilder aperture(String aperture) {
        this.aperture = aperture;
        return this;
    }

    public ExifBuilder iso(String iso) {
        this.iso = iso;
        return this;
    }

    public ExifBuilder flash(String flash) {
        this.flash = flash;
        return this;
    }

    public ExifBuilder whiteBalance(String whiteBalance) {
        this.whiteBalance = whiteBalance;
        return this;
    }

    public Exif build() {
        return new Exif(camera, lens, coordinates, focalLength, focalLength35mm, exposureTime, aperture, iso, flash,
                whiteBalance);
    }
}
