package org.alancesar.darkroom.engine.filter;

import org.alancesar.darkroom.engine.process.effect.Effect;
import org.alancesar.darkroom.engine.process.effect.filter.NightEffect;

public class Night implements Filter {

    private static final Effect FX = new NightEffect();

    @Override
    public Effect effect() {
        return FX;
    }

    @Override
    public String name() {
        return "Night";
    }

    @Override
    public String shortName() {
        return "night";
    }

}
