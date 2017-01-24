package org.alancesar.darkroom.engine.process.effect;

import java.io.File;

import org.alancesar.darkroom.engine.process.Operation;
import org.im4java.core.IMOperation;

public class ResizeEffect extends Effect {

    private static final int MINIMUM_HEIGHT = 20;

    private static int width;
    private static int height;

    public ResizeEffect(int width, int height) {
        ResizeEffect.width = width;
        ResizeEffect.height = height;
    }

    public ResizeEffect(int width) {
        this(width, -1);
    }

    @Override
    public Operation create(File input, File output) {
        IMOperation step = new IMOperation();
        step.addImage(input.getAbsolutePath());

        if (height < MINIMUM_HEIGHT) {
            step.resize(width);
        } else {
            step.resize(width, height);
        }

        step.unsharp(1.5, 1.0, 1.5, 0.02);
        step.addImage(output.getAbsolutePath());

        Operation operation = new Operation();
        operation.addStep(step);

        return operation;
    }

}
