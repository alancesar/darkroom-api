package org.alancesar.darkroom.engine.filter;

import org.alancesar.darkroom.engine.editor.Operators;
import org.im4java.core.IMOperation;

import java.io.File;
import java.util.Map;

public class Kelvin implements FilterEffect {
    @Override
    public void apply(File input) {
        Map<String, Integer> size = Operators.getImageSize(input);
        int width = size.get("width");
        int height = size.get("height");

        IMOperation sub1 = new IMOperation();
        sub1.addImage(input.getAbsolutePath());
        sub1.autoGamma();
        sub1.modulate(120d, 50d, 100d);

        IMOperation sub2 = new IMOperation();
        sub2.size(width, height);
        sub2.fill("rgba(255, 153, 0, 0.5");
        sub2.draw("rectangle 0, 0 " + width + ", " + height);

        IMOperation op = new IMOperation();
        op.addSubOperation(sub1);
        op.addSubOperation(sub2);
        op.compose("multiply");
        op.addImage(input.getAbsolutePath());

        Operators.runCommand(op);
    }
}
