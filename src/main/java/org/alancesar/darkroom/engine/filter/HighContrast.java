package org.alancesar.darkroom.engine.filter;

import org.alancesar.darkroom.engine.process.effect.Effect;
import org.alancesar.darkroom.engine.process.effect.filter.HighContrastEffect;

public class HighContrast implements Filter {

    private static final Effect FX = new HighContrastEffect();

    @Override
    public Effect effect() {
        return FX;
    }

    @Override
    public String name() {
        return "High Contrast";
    }

    @Override
    public String shortName() {
        return "highcontrast";
    }

}
