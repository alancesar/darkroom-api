package org.alancesar.darkroom.engine.process.effect.filter;

import java.io.File;

import org.alancesar.darkroom.engine.process.Operation;
import org.alancesar.darkroom.engine.process.effect.ColorToneEffect;
import org.alancesar.darkroom.engine.process.effect.Effect;
import org.im4java.core.IMOperation;

public class NashvilleEffect extends Effect {

    @Override
    public Operation create(File input, File output) {

        Operation operation = new Operation();
        operation.addStep(new ColorToneEffect("#222b6d", 100, true).create(input, output));
        operation.addStep(new ColorToneEffect("#f7daae", 100, false).create(output, output));

        IMOperation step = new IMOperation();
        step.addImage(input.getAbsolutePath());
        step.contrast();
        step.modulate(100d, 150d, 100d);
        step.autoGamma();
        step.addImage(output.getAbsolutePath());

        operation.addStep(step);

        return operation;
    }

}
