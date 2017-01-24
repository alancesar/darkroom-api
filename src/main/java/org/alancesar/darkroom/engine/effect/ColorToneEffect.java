package org.alancesar.darkroom.engine.effect;

import java.io.File;

import org.alancesar.darkroom.engine.processor.Operation;
import org.im4java.core.IMOperation;

public class ColorToneEffect extends Effect {

    private String color;
    private int level;
    private boolean negate;

    public ColorToneEffect(String color, int level, boolean negate) {
        this.color = color;
        this.level = level;
        this.negate = negate;
    }

    @Override
    public Operation create(File input, File output) {
        IMOperation step = new IMOperation();
        step.addImage(input.getAbsolutePath());

        IMOperation colorspace = new IMOperation();
        colorspace.set("colorspace", "RGB");
        step.addSubOperation(colorspace);

        IMOperation subStep1 = new IMOperation();
        subStep1.clone(0);
        subStep1.fill(color);
        subStep1.colorize();
        subStep1.addRawArgs("100%");
        step.addSubOperation(subStep1);

        IMOperation subStep2 = new IMOperation();
        subStep2.clone(0);
        subStep2.colorspace("gray");

        if (negate) {
            subStep2.negate();
        }

        step.addSubOperation(subStep2);

        step.compose("blend");
        step.define("compose:args=" + level + "," + (100 - level));
        step.composite();

        step.addImage(output.getAbsolutePath());

        Operation operation = new Operation();
        operation.addStep(step);

        return operation;
    }

}
