package org.alancesar.darkroom.engine.filter;

import org.alancesar.darkroom.engine.process.effect.Effect;
import org.alancesar.darkroom.engine.process.effect.filter.NoFilterEffect;

public class NoFilter implements Filter {
    
    private static final Effect FX = new NoFilterEffect();

    @Override
    public Effect effect() {
        return FX;
    }

    @Override
    public String name() {
        return "No Filter";
    }

    @Override
    public String shortName() {
        return "no_filter";
    }

}
