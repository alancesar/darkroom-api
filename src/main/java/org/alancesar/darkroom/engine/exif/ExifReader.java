package org.alancesar.darkroom.engine.exif;

import java.io.File;
import java.io.IOException;

import org.alancesar.darkroom.engine.dictionary.Dictionary;
import org.alancesar.darkroom.engine.dictionary.Item;
import org.alancesar.darkroom.engine.dictionary.photo.ApertureItem;
import org.alancesar.darkroom.engine.dictionary.photo.CameraItem;
import org.alancesar.darkroom.engine.dictionary.photo.CoordinatesItem;
import org.alancesar.darkroom.engine.dictionary.photo.ExposureTimeItem;
import org.alancesar.darkroom.engine.dictionary.photo.FNumberItem;
import org.alancesar.darkroom.engine.dictionary.photo.FlashItem;
import org.alancesar.darkroom.engine.dictionary.photo.FocalLength35mmItem;
import org.alancesar.darkroom.engine.dictionary.photo.FocalLengthItem;
import org.alancesar.darkroom.engine.dictionary.photo.IsoItem;
import org.alancesar.darkroom.engine.dictionary.photo.LensItem;
import org.alancesar.darkroom.engine.dictionary.photo.ShutterSpeedItem;
import org.alancesar.darkroom.engine.dictionary.photo.WhiteBalanceItem;

public class ExifReader {

    private static final ExifReader INSTANCE = new ExifReader();
    private Item<CameraExif> camera;
    private Item<LensExif> lens;
    private Item<CoordinatesExif> coordinates;
    private Item<String> focalLength;
    private Item<String> focalLength35mm;
    private Item<String> exposureTime;
    private Item<String> shutterSpeed;
    private Item<String> aperture;
    private Item<String> fNumber;
    private Item<String> iso;
    private Item<String> flash;
    private Item<String> whiteBalance;
    private Item<String >software;

    private ExifReader() {
        camera = new CameraItem();
        lens = new LensItem();
        coordinates = new CoordinatesItem();
        focalLength = new FocalLengthItem();
        focalLength35mm = new FocalLength35mmItem();
        exposureTime = new ExposureTimeItem();
        shutterSpeed = new ShutterSpeedItem();
        exposureTime.next(shutterSpeed);
        aperture = new ApertureItem();
        fNumber = new FNumberItem();
        aperture.next(fNumber);
        iso = new IsoItem();
        flash = new FlashItem();
        whiteBalance = new WhiteBalanceItem();
        software = new SoftwareItem();
    }

    public static ExifReader getInstance() {
        return INSTANCE;
    }

    public Exif readFromFile(File file) throws IOException {

        Dictionary dictionary = Dictionary.readFromFile(file);

        ExifBuilder builder = new ExifBuilder().focalLength(focalLength.read(dictionary))
                .focalLength35mm(focalLength35mm.read(dictionary)).exposureTime(exposureTime.read(dictionary))
                .aperture(aperture.read(dictionary)).iso(iso.read(dictionary)).flash(flash.read(dictionary))
                .whiteBalance(whiteBalance.read(dictionary)).camera(camera.read(dictionary)).lens(lens.read(dictionary))
                .coordinates(coordinates.read(dictionary)).software(software.read(dictionary));
        
        return builder.build();
    }
}
