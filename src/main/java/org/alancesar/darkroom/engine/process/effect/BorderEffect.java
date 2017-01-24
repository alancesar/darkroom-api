package org.alancesar.darkroom.engine.process.effect;

import java.io.File;

import org.alancesar.darkroom.engine.process.Operation;
import org.im4java.core.IMOperation;

public class BorderEffect extends Effect {

    private static final String DEFAULT_BORDER_COLOR = "black";
    private int width;
    private String color;

    public BorderEffect(int width, String color) {
        this.width = width;
        this.color = color;
    }

    public BorderEffect(int width) {
        this(width, DEFAULT_BORDER_COLOR);
    }

    @Override
    public Operation create(File input, File output) {
        IMOperation step = new IMOperation();
        step.addImage(input.getAbsolutePath());
        step.bordercolor(color);
        step.border(width);
        step.addImage(output.getAbsolutePath());

        Operation operation = new Operation();
        operation.addStep(step);

        return operation;
    }

}
