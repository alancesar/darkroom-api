package org.alancesar.darkroom.engine.dictionary.photo.lens;

import org.alancesar.darkroom.engine.dictionary.ItemIFD0DirectoryOrSubIFDDirectory;

import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;

public class LensMake extends ItemIFD0DirectoryOrSubIFDDirectory {

    @Override
    protected int ifd0() {
        return ExifIFD0Directory.TAG_LENS_MAKE;
    }

    @Override
    protected int subIfd() {
        return ExifSubIFDDirectory.TAG_LENS_MAKE;
    }

}
