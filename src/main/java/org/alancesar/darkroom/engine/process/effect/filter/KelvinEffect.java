package org.alancesar.darkroom.engine.process.effect.filter;

import java.io.File;

import org.alancesar.darkroom.engine.model.Image;
import org.alancesar.darkroom.engine.process.Operation;
import org.alancesar.darkroom.engine.process.effect.Effect;
import org.im4java.core.IMOperation;

public class KelvinEffect extends Effect {

    @Override
    public Operation create(File input, File output) {
        Image image = new Image(input);
        int width = image.getWidth();
        int height = image.getHeight();

        IMOperation subStep1 = new IMOperation();
        subStep1.addImage(input.getAbsolutePath());
        subStep1.autoGamma();
        subStep1.modulate(120d, 50d, 100d);

        IMOperation subStep2 = new IMOperation();
        subStep2.size(width, height);
        subStep2.fill("rgba(255, 153, 0, 0.5");
        subStep2.draw("rectangle 0, 0 " + width + ", " + height);

        IMOperation step = new IMOperation();
        step.addSubOperation(subStep1);
        step.addSubOperation(subStep2);
        step.compose("multiply");
        step.addImage(output.getAbsolutePath());

        Operation operation = new Operation();
        operation.addStep(step);

        return operation;
    }

}
