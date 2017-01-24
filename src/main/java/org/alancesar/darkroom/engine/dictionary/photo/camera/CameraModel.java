package org.alancesar.darkroom.engine.dictionary.photo.camera;

import org.alancesar.darkroom.engine.dictionary.Dictionary;
import org.alancesar.darkroom.engine.dictionary.Item;

import com.drew.metadata.exif.ExifSubIFDDirectory;

public class CameraModel implements Item<String> {

    @Override
    public String read(Dictionary dictionary) {
        return dictionary.getExifIFD0().getDescription(ExifSubIFDDirectory.TAG_MODEL);
    }

    @Override
    public void setNext(Item<String> dictionary) {
        return;
    }

}
