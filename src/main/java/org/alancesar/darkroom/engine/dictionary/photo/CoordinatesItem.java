package org.alancesar.darkroom.engine.dictionary.photo;

import org.alancesar.darkroom.engine.dictionary.Dictionary;
import org.alancesar.darkroom.engine.dictionary.Item;
import org.alancesar.darkroom.engine.exif.CoordinatesExif;

import com.drew.lang.GeoLocation;
import com.drew.metadata.exif.GpsDirectory;

public class CoordinatesItem implements Item<CoordinatesExif> {

    @Override
    public CoordinatesExif read(Dictionary dictionary) {

        GpsDirectory gps = dictionary.getGps();

        if (gps != null) {
            GeoLocation geoLocation = gps.getGeoLocation();

            if (geoLocation != null) {
                return new CoordinatesExif(geoLocation.getLatitude(), geoLocation.getLongitude());
            }
        }

        return null;
    }

    @Override
    public void next(Item<CoordinatesExif> next) {
        return;
    }

}
