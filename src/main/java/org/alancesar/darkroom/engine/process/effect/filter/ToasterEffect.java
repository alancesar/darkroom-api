package org.alancesar.darkroom.engine.process.effect.filter;

import java.io.File;

import org.alancesar.darkroom.engine.process.Operation;
import org.alancesar.darkroom.engine.process.effect.ColorToneEffect;
import org.alancesar.darkroom.engine.process.effect.Effect;
import org.alancesar.darkroom.engine.process.effect.VignetteEffect;
import org.im4java.core.IMOperation;

public class ToasterEffect extends Effect {

    @Override
    public Operation create(File input, File output) {

        Operation operation = new Operation();
        operation.addStep(new ColorToneEffect("#330000", 100, true).create(input, output));

        IMOperation step = new IMOperation();
        step.addImage(output.getAbsolutePath());
        step.modulate(150d, 80d, 100d);
        step.gamma(1.2);
        step.contrast();
        step.contrast();
        step.addImage(output.getAbsolutePath());

        operation.addStep(step);
        operation.addStep(new VignetteEffect("none", "LavenderBlush3").create(output, output));
        operation.addStep(new VignetteEffect("#ff9966", "none").create(output, output));

        return operation;
    }

}
