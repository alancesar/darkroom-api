package org.alancesar.darkroom.engine.exif;

public class Exif {

    private final CameraExif camera;
    private final LensExif lens;
    private final CoordinatesExif coordinates;
    private final String focalLength;
    private final String focalLength35mm;
    private final String exposureTime;
    private final String aperture;
    private final String iso;
    private final String flash;
    private final String whiteBalance;
    private final String software;

    public Exif(CameraExif camera, LensExif lens, CoordinatesExif coordinates, String focalLength,
            String focalLength35mm, String exposureTime, String aperture, String iso, String flash,
            String whiteBalance, String software) {
        this.camera = camera;
        this.lens = lens;
        this.coordinates = coordinates;
        this.focalLength = focalLength;
        this.focalLength35mm = focalLength35mm;
        this.exposureTime = exposureTime;
        this.aperture = aperture;
        this.iso = iso;
        this.flash = flash;
        this.whiteBalance = whiteBalance;
        this.software = software;
    }

    public CameraExif getCamera() {
        return camera;
    }

    public LensExif getLens() {
        return lens;
    }

    public CoordinatesExif getCoordinates() {
        return coordinates;
    }

    public String getFocalLength() {
        return focalLength;
    }

    public String getFocalLength35mm() {
        return focalLength35mm;
    }

    public String getExposureTime() {
        return exposureTime;
    }

    public String getAperture() {
        return aperture;
    }

    public String getIso() {
        return iso;
    }

    public String getFlash() {
        return flash;
    }

    public String getWhiteBalance() {
        return whiteBalance;
    }

    public String getSoftware() {
        return software;
    }

    @Override
    public String toString() {

        StringBuilder output = new StringBuilder();

        if (camera != null) {
            output.append("\nCamera")
                .append("\n  Make.................... " + camera.getMake())
                .append("\n  Model................... " + camera.getModel());
        }

        if (lens != null) {
            output.append("\nLens")
                .append("\n  Make.................... " + lens.getMake())
                .append("\n  Model................... " + lens.getModel());
        }
        
        if (coordinates != null) {
            output.append("\nCoordinates")
                .append("\n  Latitude................ " + coordinates.getLatitude())
                .append("\n  Longitude............... " + coordinates.getLongitude());
        }
        
        output
            .append("\nFocal Length.............. " + focalLength)
            .append("\nFocal Length (35mm film).. " + focalLength35mm)
            .append("\nExposure Time............. " + exposureTime)
            .append("\nAperture.................. " + aperture)
            .append("\nISO....................... " + iso)
            .append("\nFlash..................... " + flash)
            .append("\nWhite Balance............. " + whiteBalance)
            .append("\nSoftware.................. " + software);

        return output.toString();
    }
}
