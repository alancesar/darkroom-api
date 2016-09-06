package org.alancesar.darkroom.engine.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.alancesar.darkroom.engine.exif.CameraExif;
import org.alancesar.darkroom.engine.exif.Exif;
import org.alancesar.darkroom.engine.exif.LensExif;
import org.alancesar.darkroom.engine.exif.PhotoExif;
import org.alancesar.geolocation.Location;
import org.alancesar.util.ObjectUtils;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.exif.GpsDirectory;

public class ExifReader {
	public PhotoExif readFromFile(File file) throws IOException, ImageProcessingException {
		final Map<Exif, String> info = new HashMap<>();
		final Metadata metadata = ImageMetadataReader.readMetadata(file);

		Runnable runnableExifIFD0Directory = () -> {
			ExifIFD0Directory exifIFD0Directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
			if (exifIFD0Directory != null) {
				for (Tag tag : exifIFD0Directory.getTags()) {
					Exif exif = Exif.getByExifIFD0Directory(tag.getTagType());
					if (exif != null) {
						synchronized (info) {
							info.put(exif, tag.getDescription());
						}
					}
				}
			}
		};

		Runnable runnableExifSubIFDDirectory = () -> {
			ExifSubIFDDirectory exifSubIFDDirectory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
			if (exifSubIFDDirectory != null) {
				for (Tag tag : exifSubIFDDirectory.getTags()) {
					Exif exif = Exif.getByExifSubIFDDirectory(tag.getTagType());
					if (exif != null) {
						synchronized (info) {
							info.put(exif, tag.getDescription());
						}
					}
				}
			}
		};

		ExecutorService executorService = Executors.newFixedThreadPool(2);
		executorService.submit(runnableExifIFD0Directory);
		executorService.submit(runnableExifSubIFDDirectory);
		executorService.shutdown();

		try {
			executorService.awaitTermination(90, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		PhotoExif photo = new PhotoExif();
		photo.setFocalLength(info.get(Exif.FOCAL_LENGTH));
		photo.setFocalLength35mm(info.get(Exif.FOFAL_LENGTH_35MM));
		photo.setExposureTime(
				(String) ObjectUtils.firstNotNull(info.get(Exif.EXPOSURE_TIME), info.get(Exif.SHUTTER_SPEED)));
		photo.setAperture((String) ObjectUtils.firstNotNull(info.get(Exif.APERTURE), info.get(Exif.F_NUMBER)));
		photo.setIso(info.get(Exif.ISO));
		photo.setFlash(info.get(Exif.FLASH));
		photo.setWhiteBalance(info.get(Exif.WHITE_BALANCE));

		if (info.get(Exif.CAMERA_MAKE) != null || info.get(Exif.CAMERA_MODEL) != null) {
			CameraExif camera = new CameraExif();
			camera.setMake(info.get(Exif.CAMERA_MAKE));
			camera.setModel(info.get(Exif.CAMERA_MODEL));
			photo.setCamera(camera);
		}

		if (info.get(Exif.LENS_MAKE) != null || info.get(Exif.LENS_MODEL) != null) {
			LensExif lens = new LensExif();
			lens.setMake(info.get(Exif.LENS_MAKE));
			lens.setModel(info.get(Exif.LENS_MODEL));
			photo.setLens(lens);
		}

		GpsDirectory gpsDirectory = metadata.getFirstDirectoryOfType(GpsDirectory.class);

		if (gpsDirectory != null) {
			GeoLocation geoLocation = gpsDirectory.getGeoLocation();

			if (geoLocation != null) {
				double lat = geoLocation.getLatitude();
				double lng = geoLocation.getLongitude();
				photo.setLocation(new Location(lat, lng));
			}
		}

		return photo;
	}
}
