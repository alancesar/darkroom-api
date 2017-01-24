package org.alancesar.darkroom.engine.filter;

import org.alancesar.darkroom.engine.effect.Effect;
import org.alancesar.darkroom.engine.effect.filter.KelvinEffect;

public class Kelvin implements Filter {

    private static final Effect FX = new KelvinEffect();

    @Override
    public Effect effect() {
        return FX;
    }

    @Override
    public String name() {
        return "Kelvin";
    }

    @Override
    public String shortName() {
        return "kelvin";
    }

}
