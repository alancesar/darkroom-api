package org.alancesar.darkroom.engine.filter;

import org.alancesar.darkroom.engine.process.effect.Effect;
import org.alancesar.darkroom.engine.process.effect.filter.GrayscaleEffect;

public class Grayscale implements Filter {
    
    private static final Effect FX = new GrayscaleEffect(); 

    @Override
    public Effect effect() {
        return FX;
    }

    @Override
    public String name() {
        return "Grayscale";
    }

    @Override
    public String shortName() {
        return "grayscale";
    }

}
