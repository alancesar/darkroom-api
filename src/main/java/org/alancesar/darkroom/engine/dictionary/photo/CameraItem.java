package org.alancesar.darkroom.engine.dictionary.photo;

import org.alancesar.darkroom.engine.dictionary.Dictionary;
import org.alancesar.darkroom.engine.dictionary.Item;
import org.alancesar.darkroom.engine.dictionary.photo.camera.CameraMake;
import org.alancesar.darkroom.engine.dictionary.photo.camera.CameraModel;
import org.alancesar.darkroom.engine.exif.CameraExif;

public class CameraItem implements Item<CameraExif> {

    @Override
    public CameraExif read(Dictionary dictionary) {
        String make = new CameraMake().read(dictionary);
        String model = new CameraModel().read(dictionary);

        if (make != null || model != null) {
            return new CameraExif(make, model);
        }

        return null;
    }

    @Override
    public void next(Item<CameraExif> next) {
        return;
    }

}
