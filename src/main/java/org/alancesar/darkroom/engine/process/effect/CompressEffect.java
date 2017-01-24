package org.alancesar.darkroom.engine.process.effect;

import java.io.File;

import org.alancesar.darkroom.engine.process.Operation;
import org.im4java.core.IMOperation;

public class CompressEffect extends Effect {

    private static final double DEFAULT_COMPRESS_QUALITY = 20;
    private double quality;

    public CompressEffect(double quality) {
        this.quality = quality;
    }
    
    public CompressEffect() {
        this(DEFAULT_COMPRESS_QUALITY);
    }

    @Override
    public Operation create(File input, File output) {
        if (quality < 0.0 || quality > 100.0) {
            quality = DEFAULT_COMPRESS_QUALITY;
        }

        IMOperation step = new IMOperation();
        step.addImage(input.getAbsolutePath());
        step.strip();
        step.interlace("Plane");
        step.gaussianBlur(0.05);
        step.quality(quality);
        step.addImage(output.getAbsolutePath());

        Operation operation = new Operation();
        operation.addStep(step);

        return operation;
    }

}
