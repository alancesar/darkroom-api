package org.alancesar.darkroom.engine.process.effect;

import java.io.File;

import org.alancesar.darkroom.engine.model.Image;
import org.alancesar.darkroom.engine.process.Operation;
import org.im4java.core.IMOperation;

public class VignetteEffect extends Effect {

    private static final double DEFAULT_CROP_FACTOR = 1.5;
    private String primaryColor;
    private String secondaryColor;
    private double cropFactor;

    public VignetteEffect(String primaryColor, String secondaryColor, double cropFactor) {
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.cropFactor = cropFactor;
    }

    public VignetteEffect(String primaryColor, String secondaryColor) {
        this(primaryColor, secondaryColor, DEFAULT_CROP_FACTOR);
    }

    public VignetteEffect() {
        this("none", "black", DEFAULT_CROP_FACTOR);
    }

    @Override
    public Operation create(File input, File output) {
        Image image = new Image(input);

        IMOperation step = new IMOperation();

        IMOperation subStep1 = new IMOperation();
        subStep1.addImage(input.getAbsolutePath());

        step.addSubOperation(subStep1);

        IMOperation subStep2 = new IMOperation();
        subStep2.size((int) (image.getWidth() * cropFactor), (int) (image.getHeight() * cropFactor));

        String radialGradiente = "radial-gradient:" + primaryColor + "-" + secondaryColor;

        subStep2.addRawArgs(radialGradiente);
        subStep2.gravity("center");
        subStep2.crop(image.getWidth(), image.getHeight(), 0, 0);
        subStep2.p_repage();

        step.addSubOperation(subStep2);
        step.compose("multiply");
        step.flatten();
        step.addImage(output.getAbsolutePath());

        Operation operation = new Operation();
        operation.addStep(step);

        return operation;
    }

}
