package org.alancesar.darkroom.engine.filter;

import org.alancesar.darkroom.engine.effect.Effect;
import org.alancesar.darkroom.engine.effect.filter.NormalizeEffect;

public class Normalize implements Filter {

    private static final Effect FX = new NormalizeEffect();

    @Override
    public Effect effect() {
        return FX;
    }

    @Override
    public String name() {
        return "Normalize";
    }

    @Override
    public String shortName() {
        return "normalize";
    }

}
