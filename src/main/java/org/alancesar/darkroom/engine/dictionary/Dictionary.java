package org.alancesar.darkroom.engine.dictionary;

import java.io.File;
import java.io.IOException;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.exif.GpsDirectory;

public class Dictionary {

    private Directory exifSubIFD;
    private Directory exifIFD0;
    private GpsDirectory gps;

    private Dictionary() {
    }

    public static Dictionary readFromFile(File file) throws IOException {

        try {
            Metadata metadata = ImageMetadataReader.readMetadata(file);
            Dictionary dictionary = new Dictionary();
            dictionary.exifIFD0 = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
            dictionary.exifSubIFD = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
            dictionary.gps = metadata.getFirstDirectoryOfType(GpsDirectory.class);

            return dictionary;
        } catch (ImageProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Directory getExifSubIFD() {
        return exifSubIFD;
    }

    public Directory getExifIFD0() {
        return exifIFD0;
    }

    public GpsDirectory getGps() {
        return gps;
    }

}
