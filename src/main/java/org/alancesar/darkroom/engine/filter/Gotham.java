package org.alancesar.darkroom.engine.filter;

import org.alancesar.darkroom.engine.process.effect.Effect;
import org.alancesar.darkroom.engine.process.effect.filter.GothamEffect;

public class Gotham implements Filter {

    private static final Effect FX = new GothamEffect();

    @Override
    public Effect effect() {
        return FX;
    }

    @Override
    public String name() {
        return "Gotham";
    }

    @Override
    public String shortName() {
        return "gotham";
    }

}
