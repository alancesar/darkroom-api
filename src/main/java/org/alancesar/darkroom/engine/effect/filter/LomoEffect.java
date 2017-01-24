package org.alancesar.darkroom.engine.effect.filter;

import java.io.File;

import org.alancesar.darkroom.engine.effect.Effect;
import org.alancesar.darkroom.engine.effect.VignetteEffect;
import org.alancesar.darkroom.engine.processor.Operation;
import org.im4java.core.IMOperation;

public class LomoEffect extends Effect {

    @Override
    public Operation create(File input, File output) {

        IMOperation step = new IMOperation();
        step.addImage(input.getAbsolutePath());
        step.channel("R");
        step.level();
        step.addRawArgs("33%");
        step.channel("G");
        step.level();
        step.addRawArgs("33%");
        step.addImage(output.getAbsolutePath());

        Operation operation = new Operation();
        operation.addStep(step);
        operation.addStep(new VignetteEffect().create(output, output));

        return operation;
    }

}
