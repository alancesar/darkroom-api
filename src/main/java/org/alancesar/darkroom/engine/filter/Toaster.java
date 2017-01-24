package org.alancesar.darkroom.engine.filter;

import org.alancesar.darkroom.engine.effect.Effect;
import org.alancesar.darkroom.engine.effect.filter.ToasterEffect;

public class Toaster implements Filter {

    private static final Effect FX = new ToasterEffect();

    @Override
    public Effect effect() {
        return FX;
    }

    @Override
    public String name() {
        return "Toaster";
    }

    @Override
    public String shortName() {
        return "toaster";
    }

}
