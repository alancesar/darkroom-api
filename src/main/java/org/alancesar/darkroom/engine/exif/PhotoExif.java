package org.alancesar.darkroom.engine.exif;

import org.alancesar.geolocation.Location;

public class PhotoExif {

	private CameraExif camera;
	private LensExif lens;
	private String focalLength;
	private String focalLength35mm;
	private String exposureTime;
	private String aperture;
	private String iso;
	private String flash;
	private String whiteBalance;
	private Location location;

	public CameraExif getCamera() {
		return camera;
	}

	public void setCamera(CameraExif camera) {
		this.camera = camera;
	}

	public LensExif getLens() {
		return lens;
	}

	public void setLens(LensExif lens) {
		this.lens = lens;
	}

	public String getFocalLength() {
		return focalLength;
	}

	public void setFocalLength(String focalLength) {
		this.focalLength = focalLength;
	}

	public String getFocalLength35mm() {
		return focalLength35mm;
	}

	public void setFocalLength35mm(String focalLength35mm) {
		this.focalLength35mm = focalLength35mm;
	}

	public String getExposureTime() {
		return exposureTime;
	}

	public void setExposureTime(String exposureTime) {
		this.exposureTime = exposureTime;
	}

	public String getAperture() {
		return aperture;
	}

	public void setAperture(String aperture) {
		this.aperture = aperture;
	}

	public String getIso() {
		return iso;
	}

	public void setIso(String iso) {
		this.iso = iso;
	}

	public String getFlash() {
		return flash;
	}

	public void setFlash(String flash) {
		this.flash = flash;
	}

	public String getWhiteBalance() {
		return whiteBalance;
	}

	public void setWhiteBalance(String whiteBalance) {
		this.whiteBalance = whiteBalance;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}
