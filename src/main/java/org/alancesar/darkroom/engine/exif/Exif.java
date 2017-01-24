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

    public Exif(CameraExif camera, LensExif lens, CoordinatesExif coordinates, String focalLength, String focalLength35mm,
            String exposureTime, String aperture, String iso, String flash, String whiteBalance) {
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
    }

    public CameraExif getCamera() {
        return camera;
    }

    public LensExif getLens() {
        return lens;
    }
    
    public CoordinatesExif getLocation() {
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

    @Override
    public String toString() {

        StringBuilder output = new StringBuilder()
                .append(camera != null ? "Camera\n" + "  Make.................... " + camera.getMake() + "\n" +
                        "  Model................... " + camera.getModel() + "\n" : "")
                .append(lens != null ? "Lens\n" + "  Make.................... " + lens.getMake() + "\n" +
                        "  Model................... " + lens.getModel() + "\n" : "")
                .append(coordinates != null ? "Location.................. " + coordinates.getLatitude() + ", " +
                        coordinates.getLongitude() + "\n" : "")
                .append("Focal Length.............. " + focalLength + "\n")
                .append("Focal Length (35mm film).. " + focalLength35mm + "\n")
                .append("Exposure Time............. " + exposureTime + "\n")
                .append("Aperture.................. " + aperture + "\n")
                .append("ISO....................... " + iso + "\n").append("Flash..................... " + flash + "\n")
                .append("White Balance............. " + whiteBalance);

        return output.toString();
    }
}
