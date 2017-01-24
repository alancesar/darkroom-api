package org.alancesar.darkroom.engine.effect.filter;

import java.io.File;

import org.alancesar.darkroom.engine.effect.BorderEffect;
import org.alancesar.darkroom.engine.effect.Effect;
import org.alancesar.darkroom.engine.model.Image;
import org.alancesar.darkroom.engine.processor.Operation;
import org.im4java.core.IMOperation;

public class GothamEffect extends Effect {

    @Override
    public Operation create(File input, File output) {
        Image image = new Image(input);

        int border = (int) (image.getWidth() * 0.05);

        IMOperation step = new IMOperation();
        step.addImage(input.getAbsolutePath());
        step.modulate(120d, 10d, 100d);
        step.fill("#222b6d");
        step.colorize(20);
        step.gamma(0.5);
        step.contrast();
        step.contrast();
        step.shave(border);
        step.addImage(output.getAbsolutePath());

        Operation operation = new Operation();
        operation.addStep(step);
        operation.addStep(new BorderEffect(border).create(output, output));

        return operation;
    }

}
