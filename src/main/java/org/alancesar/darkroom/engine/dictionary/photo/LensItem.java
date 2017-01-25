package org.alancesar.darkroom.engine.dictionary.photo;

import org.alancesar.darkroom.engine.dictionary.Dictionary;
import org.alancesar.darkroom.engine.dictionary.Item;
import org.alancesar.darkroom.engine.dictionary.photo.lens.LensMake;
import org.alancesar.darkroom.engine.dictionary.photo.lens.LensModel;
import org.alancesar.darkroom.engine.exif.LensExif;

public class LensItem implements Item<LensExif> {

    @Override
    public LensExif read(Dictionary dictionary) {
        String make = new LensMake().read(dictionary);
        String model = new LensModel().read(dictionary);

        if (make != null || model != null) {
            return new LensExif(make, model);
        }

        return null;
    }

    @Override
    public void next(Item<LensExif> next) {
        return;
    }

}
