package org.alancesar.darkroom.engine.editor;

import org.im4java.core.IMOperation;

import java.io.File;
import java.util.Map;

public class Effect {
    private File input;

    private static final int DEFAULT_BORDER_SIZE = 20;
    private static final String DEFAULT_BORDER_COLOR = "black";
    private static final double DEFAULT_CROP_FACTOR = 1.5;

    public Effect(File input) {
        this.input = input;
    }

    public void colorTone(String color, int level, boolean negate) {
        IMOperation op = new IMOperation();

        op.addImage(input.getAbsolutePath());
        op.set("colorspace", "RGB");

        IMOperation sub1 = new IMOperation();
        sub1.clone(0);
        sub1.fill(color);
        sub1.colorize();
        sub1.addRawArgs("100%");
        op.addSubOperation(sub1);

        IMOperation sub2 = new IMOperation();
        sub2.clone(0);
        sub2.colorspace("gray");
        if (negate)
            sub2.negate();
        op.addSubOperation(sub2);

        op.compose("blend");
        op.define("compose:args=" + level + "," + (100 - level));
        op.composite();

        op.addImage(input.getAbsolutePath());

        Operators.runCommand(op);
    }

    public void vignette(String primaryColor, String secondaryColor, Double cropFactor) {
        Map<String, Integer> size = Operators.getImageSize(input);
        int width = size.get("width");
        int height = size.get("height");
        Double x = width * cropFactor;
        Double y = height * cropFactor;

        IMOperation op = new IMOperation();

        IMOperation sub1 = new IMOperation();
        sub1.addImage(input.getAbsolutePath());
        op.addSubOperation(sub1);

        IMOperation sub2 = new IMOperation();
        sub2.size(x.intValue(), y.intValue());
        sub2.addRawArgs(radialGradient(primaryColor, secondaryColor));
        sub2.gravity("center");
        sub2.crop(width, height, 0, 0);
        sub2.p_repage();
        op.addSubOperation(sub2);
        op.compose("multiply");
        op.flatten();
        op.addImage(input.getAbsolutePath());

        Operators.runCommand(op);
    }

    public void vignette(String primaryColor, String secondaryColor) {
        vignette(primaryColor, secondaryColor, DEFAULT_CROP_FACTOR);
    }

    public void vignette() {
        vignette("none", "black", DEFAULT_CROP_FACTOR);
    }

    public void border(String color, int width) {
        IMOperation op = new IMOperation();
        op.addImage(input.getAbsolutePath());
        op.bordercolor(color);
        op.border(width);
        op.addImage(input.getAbsolutePath());
        Operators.runCommand(op);
    }

    public void border() {
        border(DEFAULT_BORDER_COLOR, DEFAULT_BORDER_SIZE);
    }

    public String radialGradient(String primaryColor, String secondaryColor) {
        return "radial-gradient:" + primaryColor + "-" + secondaryColor;
    }
}
