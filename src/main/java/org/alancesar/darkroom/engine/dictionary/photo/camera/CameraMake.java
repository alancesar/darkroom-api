package org.alancesar.darkroom.engine.dictionary.photo.camera;

import org.alancesar.darkroom.engine.dictionary.Dictionary;
import org.alancesar.darkroom.engine.dictionary.Item;

import com.drew.metadata.exif.ExifSubIFDDirectory;

public class CameraMake implements Item<String> {

    @Override
    public String read(Dictionary dictionary) {
        return dictionary.getExifSubIFD().getDescription(ExifSubIFDDirectory.TAG_MAKE);
    }

    @Override
    public void setNext(Item<String> dictionary) {
        return;
    }

}
