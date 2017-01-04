package org.alancesar.darkroom.engine.exif;

import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;

public enum ExifDictionary {

    CAMERA_MAKE(ExifSubIFDDirectory.TAG_MAKE, ExifSubIFDDirectory.TAG_MAKE),

    CAMERA_MODEL(ExifSubIFDDirectory.TAG_MODEL, ExifSubIFDDirectory.TAG_MODEL),

    LENS_MAKE(ExifSubIFDDirectory.TAG_LENS_MAKE, ExifSubIFDDirectory.TAG_LENS_MAKE),

    LENS_MODEL(ExifSubIFDDirectory.TAG_LENS_MODEL, ExifSubIFDDirectory.TAG_LENS_MODEL),

    FOCAL_LENGTH(ExifIFD0Directory.TAG_FOCAL_LENGTH, ExifSubIFDDirectory.TAG_FOCAL_LENGTH),

    FOFAL_LENGTH_35MM(ExifIFD0Directory.TAG_35MM_FILM_EQUIV_FOCAL_LENGTH,
            ExifSubIFDDirectory.TAG_35MM_FILM_EQUIV_FOCAL_LENGTH),

    EXPOSURE_TIME(ExifIFD0Directory.TAG_EXPOSURE_TIME, ExifSubIFDDirectory.TAG_EXPOSURE_TIME),

    SHUTTER_SPEED(ExifIFD0Directory.TAG_SHUTTER_SPEED, ExifSubIFDDirectory.TAG_SHUTTER_SPEED),

    APERTURE(ExifIFD0Directory.TAG_APERTURE, ExifSubIFDDirectory.TAG_APERTURE),

    F_NUMBER(ExifIFD0Directory.TAG_FNUMBER, ExifSubIFDDirectory.TAG_FNUMBER),

    ISO(ExifIFD0Directory.TAG_ISO_EQUIVALENT, ExifSubIFDDirectory.TAG_ISO_EQUIVALENT),

    FLASH(ExifIFD0Directory.TAG_FLASH, ExifSubIFDDirectory.TAG_FLASH),

    WHITE_BALANCE(ExifIFD0Directory.TAG_WHITE_BALANCE, ExifSubIFDDirectory.TAG_WHITE_BALANCE);

    public int exifIFD0Directory;
    public int exifSubIFDDirectory;

    ExifDictionary(int exifIFD0Directory, int exifSubIFDDirectory) {
        this.exifIFD0Directory = exifIFD0Directory;
        this.exifSubIFDDirectory = exifSubIFDDirectory;
    }

    public static ExifDictionary getByExifIFD0Directory(int exifIFD0Directory) {
        for (ExifDictionary dictionary : ExifDictionary.values()) {
            if (dictionary.exifIFD0Directory == exifIFD0Directory) {
                return dictionary;
            }
        }

        return null;
    }

    public static ExifDictionary getByExifSubIFDDirectory(int exifSubIFDDirectory) {
        for (ExifDictionary dictionary : ExifDictionary.values()) {
            if (dictionary.exifSubIFDDirectory == exifSubIFDDirectory) {
                return dictionary;
            }
        }

        return null;
    }
}
