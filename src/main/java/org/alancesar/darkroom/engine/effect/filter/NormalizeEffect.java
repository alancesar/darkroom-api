package org.alancesar.darkroom.engine.effect.filter;

import java.io.File;

import org.alancesar.darkroom.engine.effect.Effect;
import org.alancesar.darkroom.engine.processor.Operation;
import org.im4java.core.IMOperation;

public class NormalizeEffect extends Effect {

    @Override
    public Operation create(File input, File output) {
        
        IMOperation step = new IMOperation();
        step.addImage(input.getAbsolutePath());
        step.normalize();
        step.contrast();
        step.contrast();
        step.addImage(output.getAbsolutePath());
        
        Operation operation = new Operation();
        operation.addStep(step);
        
        return operation;
    }
    

}
