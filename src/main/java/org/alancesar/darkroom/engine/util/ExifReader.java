package org.alancesar.darkroom.engine.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.alancesar.darkroom.engine.exif.CameraExif;
import org.alancesar.darkroom.engine.exif.ExifDictionary;
import org.alancesar.darkroom.engine.exif.LensExif;
import org.alancesar.darkroom.engine.exif.LocationExif;
import org.alancesar.darkroom.engine.exif.Exif;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.exif.GpsDirectory;

public class ExifReader {

    public Exif readFromFile(File file) throws IOException, ImageProcessingException {

        final Map<ExifDictionary, String> info = new HashMap<>();
        final Metadata metadata = ImageMetadataReader.readMetadata(file);

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(() -> {
            ExifIFD0Directory exifIFD0Directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
            if (exifIFD0Directory != null) {
                for (Tag tag : exifIFD0Directory.getTags()) {
                    ExifDictionary dictionary = ExifDictionary.getByExifIFD0Directory(tag.getTagType());
                    if (dictionary != null) {
                        synchronized (info) {
                            info.put(dictionary, tag.getDescription());
                        }
                    }
                }
            }
        });

        executorService.submit(() -> {
            ExifSubIFDDirectory exifSubIFDDirectory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
            if (exifSubIFDDirectory != null) {
                for (Tag tag : exifSubIFDDirectory.getTags()) {
                    ExifDictionary dictionary = ExifDictionary.getByExifSubIFDDirectory(tag.getTagType());
                    if (dictionary != null) {
                        synchronized (info) {
                            info.put(dictionary, tag.getDescription());
                        }
                    }
                }
            }
        });

        executorService.shutdown();

        try {
            executorService.awaitTermination(90, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Exif exif = new Exif();

        exif.setFocalLength(info.get(ExifDictionary.FOCAL_LENGTH));
        exif.setFocalLength35mm(info.get(ExifDictionary.FOFAL_LENGTH_35MM));
        exif.setExposureTime(Optional.ofNullable(info.get(ExifDictionary.EXPOSURE_TIME))
                .orElse(info.get(ExifDictionary.SHUTTER_SPEED)));
        exif.setAperture(
                Optional.ofNullable(info.get(ExifDictionary.APERTURE)).orElse(info.get(ExifDictionary.F_NUMBER)));
        exif.setIso(info.get(ExifDictionary.ISO));
        exif.setFlash(info.get(ExifDictionary.FLASH));
        exif.setWhiteBalance(info.get(ExifDictionary.WHITE_BALANCE));

        if (info.get(ExifDictionary.CAMERA_MAKE) != null || info.get(ExifDictionary.CAMERA_MODEL) != null) {
            CameraExif camera = new CameraExif();
            camera.setMake(info.get(ExifDictionary.CAMERA_MAKE));
            camera.setModel(info.get(ExifDictionary.CAMERA_MODEL));
            exif.setCamera(camera);
        }

        if (info.get(ExifDictionary.LENS_MAKE) != null || info.get(ExifDictionary.LENS_MODEL) != null) {
            LensExif lens = new LensExif();
            lens.setMake(info.get(ExifDictionary.LENS_MAKE));
            lens.setModel(info.get(ExifDictionary.LENS_MODEL));
            exif.setLens(lens);
        }

        GpsDirectory gpsDirectory = metadata.getFirstDirectoryOfType(GpsDirectory.class);

        if (gpsDirectory != null) {
            GeoLocation geoLocation = gpsDirectory.getGeoLocation();

            if (geoLocation != null) {
                LocationExif location = new LocationExif();
                location.setLatitude(geoLocation.getLatitude());
                location.setLongitude(geoLocation.getLongitude());

                exif.setLocation(location);
            }
        }

        return exif;
    }
}
