package org.alancesar.darkroom.engine.dictionary.photo.camera;

import org.alancesar.darkroom.engine.dictionary.ItemIFD0DirectoryOrSubIFDDirectory;

import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;

public class CameraMake extends ItemIFD0DirectoryOrSubIFDDirectory {

    @Override
    protected int ifd0() {
        return ExifIFD0Directory.TAG_MAKE;
    }

    @Override
    protected int subIfd() {
        return ExifSubIFDDirectory.TAG_MAKE;
    }

}
