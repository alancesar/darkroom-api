package org.alancesar.darkroom.engine.dictionary.photo.lens;

import org.alancesar.darkroom.engine.dictionary.Dictionary;
import org.alancesar.darkroom.engine.dictionary.Item;

import com.drew.metadata.exif.ExifSubIFDDirectory;

public class LensMake implements Item<String> {

    @Override
    public String read(Dictionary dictionary) {
        return dictionary.getExifSubIFD().getDescription(ExifSubIFDDirectory.TAG_LENS_MAKE);
    }

    @Override
    public void setNext(Item<String> dictionary) {
        return;
    }

}
