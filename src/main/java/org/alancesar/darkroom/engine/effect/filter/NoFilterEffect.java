package org.alancesar.darkroom.engine.effect.filter;

import java.io.File;

import org.alancesar.darkroom.engine.effect.Effect;
import org.alancesar.darkroom.engine.processor.Operation;

public class NoFilterEffect extends Effect {

    @Override
    protected Operation create(File input, File output) {
        return new Operation();
    }

}
