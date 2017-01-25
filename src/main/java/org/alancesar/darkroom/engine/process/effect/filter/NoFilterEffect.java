package org.alancesar.darkroom.engine.process.effect.filter;

import java.io.File;

import org.alancesar.darkroom.engine.process.Operation;
import org.alancesar.darkroom.engine.process.effect.Effect;

public class NoFilterEffect extends Effect {

    @Override
    public Operation create(File input, File output) {
        return new Operation();
    }

}
