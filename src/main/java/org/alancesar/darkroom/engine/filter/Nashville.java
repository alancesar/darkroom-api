package org.alancesar.darkroom.engine.filter;

import org.alancesar.darkroom.engine.effect.Effect;
import org.alancesar.darkroom.engine.effect.filter.NashvilleEffect;

public class Nashville implements Filter {

    private static final Effect FX = new NashvilleEffect();

    @Override
    public Effect effect() {
        return FX;
    }

    @Override
    public String name() {
        return "Nashvile";
    }

    @Override
    public String shortName() {
        return "nashvile";
    }

}
