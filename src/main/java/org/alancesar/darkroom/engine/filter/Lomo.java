package org.alancesar.darkroom.engine.filter;

import org.alancesar.darkroom.engine.effect.Effect;
import org.alancesar.darkroom.engine.effect.filter.LomoEffect;

public class Lomo implements Filter {

    private static final Effect FX = new LomoEffect();

    @Override
    public Effect effect() {
        return FX;
    }

    @Override
    public String name() {
        return "Lomo";
    }

    @Override
    public String shortName() {
        return "lomo";
    }

}
