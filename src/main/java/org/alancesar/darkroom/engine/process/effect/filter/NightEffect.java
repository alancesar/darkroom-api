package org.alancesar.darkroom.engine.process.effect.filter;

import java.io.File;

import org.alancesar.darkroom.engine.process.Operation;
import org.alancesar.darkroom.engine.process.effect.Effect;
import org.im4java.core.IMOperation;

public class NightEffect extends Effect {

    @Override
    public Operation create(File input, File output) {
        IMOperation step = new IMOperation();
        step.addImage(input.getAbsolutePath());
        
        IMOperation subStep1 = new IMOperation();
        subStep1.addImage(input.getAbsolutePath());
        subStep1.type("grayscale");
        subStep1.compose("plus");
        
        IMOperation subStep2 = new IMOperation();
        subStep2.addImage(input.getAbsolutePath());
        subStep2.compose("overlay");
        
        step.addSubOperation(subStep1);
        step.addSubOperation(subStep2);
        step.composite();
        step.addImage(output.getAbsolutePath());

        Operation operation = new Operation();
        operation.addStep(step);

        return operation;
    }

}
