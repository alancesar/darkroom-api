package org.alancesar.darkroom.engine.filter;

import org.alancesar.darkroom.engine.editor.Effect;
import org.alancesar.darkroom.engine.editor.Operators;
import org.im4java.core.IMOperation;

import java.io.File;

public class Lomo implements FilterEffect {
    @Override
    public void apply(File input) {
        IMOperation op = new IMOperation();
        op.addImage(input.getAbsolutePath());
        op.channel("R");
        op.level();
        op.addRawArgs("33%");
        op.channel("G");
        op.level();
        op.addRawArgs("33%");
        op.addImage(input.getAbsolutePath());
        Operators.runCommand(op);
        new Effect(input).vignette();
    }
}
