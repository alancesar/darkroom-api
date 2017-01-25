package org.alancesar.darkroom.engine.process.effect.filter;

import java.io.File;

import org.alancesar.darkroom.engine.process.Operation;
import org.alancesar.darkroom.engine.process.effect.Effect;
import org.im4java.core.IMOperation;

public class GrayscaleEffect extends Effect {

    @Override
    public Operation create(File input, File output) {
        IMOperation step = new IMOperation();
        step.addImage(input.getAbsolutePath());
        step.type("grayscale");
        step.addImage(output.getAbsolutePath());

        Operation operation = new Operation();
        operation.addStep(step);

        return operation;
    }

}
