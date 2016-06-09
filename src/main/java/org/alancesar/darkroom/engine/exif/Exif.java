package org.alancesar.darkroom.engine.exif;

import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;

public enum Exif {
	cameraMake(ExifSubIFDDirectory.TAG_MAKE, ExifSubIFDDirectory.TAG_MAKE),
	cameraModel(ExifSubIFDDirectory.TAG_MODEL, ExifSubIFDDirectory.TAG_MODEL),
	lensMake(ExifSubIFDDirectory.TAG_LENS_MAKE, ExifSubIFDDirectory.TAG_LENS_MAKE),
	lensModel(ExifSubIFDDirectory.TAG_LENS_MODEL, ExifSubIFDDirectory.TAG_LENS_MODEL),
	focalLength(ExifIFD0Directory.TAG_FOCAL_LENGTH, ExifSubIFDDirectory.TAG_FOCAL_LENGTH),
	focalLength35mm(ExifIFD0Directory.TAG_35MM_FILM_EQUIV_FOCAL_LENGTH, ExifSubIFDDirectory.TAG_35MM_FILM_EQUIV_FOCAL_LENGTH),
	exposureTime(ExifIFD0Directory.TAG_EXPOSURE_TIME, ExifSubIFDDirectory.TAG_EXPOSURE_TIME),
	shutterSpeed(ExifIFD0Directory.TAG_SHUTTER_SPEED, ExifSubIFDDirectory.TAG_SHUTTER_SPEED),
	aperture(ExifIFD0Directory.TAG_APERTURE, ExifSubIFDDirectory.TAG_APERTURE),
	fNumber(ExifIFD0Directory.TAG_FNUMBER, ExifSubIFDDirectory.TAG_FNUMBER),
	iso(ExifIFD0Directory.TAG_ISO_EQUIVALENT, ExifSubIFDDirectory.TAG_ISO_EQUIVALENT),
	flash(ExifIFD0Directory.TAG_FLASH, ExifSubIFDDirectory.TAG_FLASH),
	whiteBalance(ExifIFD0Directory.TAG_WHITE_BALANCE, ExifSubIFDDirectory.TAG_WHITE_BALANCE);
	
	public int exifIFD0Directory;
	public int exifSubIFDDirectory;
	
	Exif(int exifIFD0Directory, int exifSubIFDDirectory) {
		this.exifIFD0Directory = exifIFD0Directory;
		this.exifSubIFDDirectory = exifSubIFDDirectory;
	}
	
	public static Exif getByExifIFD0Directory(int exifIFD0Directory) {
		for (Exif exif : Exif.values()) {
			if (exif.exifIFD0Directory == exifIFD0Directory)
				return exif;
		}
		
		return null;
	}
	
	public static Exif getByExifSubIFDDirectory(int exifSubIFDDirectory) {
		for (Exif exif : Exif.values()) {
			if (exif.exifSubIFDDirectory == exifSubIFDDirectory) 
				return exif;
		}
		
		return null;
	}
}
