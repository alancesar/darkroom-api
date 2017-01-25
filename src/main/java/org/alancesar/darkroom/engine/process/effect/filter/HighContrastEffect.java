package org.alancesar.darkroom.engine.process.effect.filter;

import java.io.File;

import org.alancesar.darkroom.engine.process.Operation;
import org.alancesar.darkroom.engine.process.effect.Effect;
import org.im4java.core.IMOperation;

public class HighContrastEffect extends Effect {
    
    private static final Effect grayscale = new GrayscaleEffect();

    @Override
    public Operation create(File input, File output) {
        
        File temp = new File(output.getAbsoluteFile() + ".tmp");
        
        Operation operation = new Operation();
        operation.addStep(grayscale.create(input, temp));
        
        IMOperation step = new IMOperation();
        step.addImage(input.getAbsolutePath());
        
        IMOperation subStep = new IMOperation();
        subStep.addImage(temp.getAbsolutePath());
        subStep.compose("multiply");
        
        step.addSubOperation(subStep);
        step.composite();
        step.addImage(output.getAbsolutePath());
        
        operation.addStep(step);
        temp.delete();

        return operation;
    }

}
