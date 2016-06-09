package org.alancesar.darkroom.engine.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.alancesar.darkroom.engine.exif.Camera;
import org.alancesar.darkroom.engine.exif.Exif;
import org.alancesar.darkroom.engine.exif.Lens;
import org.alancesar.darkroom.engine.exif.Photo;
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
	public Photo readFromFile(File file) throws IOException, ImageProcessingException {
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
	    
        Photo photo = new Photo();
        photo.setFocalLength(info.get(Exif.focalLength));
        photo.setFocalLength35mm(info.get(Exif.focalLength35mm));
        photo.setExposureTime((String)ObjectUtils.firstNotNull(
        		info.get(Exif.exposureTime), info.get(Exif.shutterSpeed)));
        photo.setAperture((String)ObjectUtils.firstNotNull(
        		info.get(Exif.aperture), info.get(Exif.fNumber)));
        photo.setIso(info.get(Exif.iso));
        photo.setFlash(info.get(Exif.flash));
        photo.setWhiteBalance(info.get(Exif.whiteBalance));
        
        if (info.get(Exif.cameraMake) != null || info.get(Exif.cameraModel) != null) {
        	Camera camera = new Camera();
        	camera.setMake(info.get(Exif.cameraMake));
        	camera.setModel(info.get(Exif.cameraModel));
        	photo.setCamera(camera);
        }
        
        if (info.get(Exif.lensMake) != null || info.get(Exif.lensModel) != null) {
        	Lens lens = new Lens();
        	lens.setMake(info.get(Exif.lensMake));
        	lens.setModel(info.get(Exif.lensModel));
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
