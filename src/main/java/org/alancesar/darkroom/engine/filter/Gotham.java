package org.alancesar.darkroom.engine.filter;

import org.alancesar.darkroom.engine.editor.Effect;
import org.alancesar.darkroom.engine.editor.Operators;
import org.im4java.core.IMOperation;

import java.io.File;

public class Gotham implements FilterEffect {
    @Override
    public void apply(File input) {
        IMOperation op = new IMOperation();
        op.addImage(input.getAbsolutePath());
        op.modulate(120d, 10d, 100d);
        op.fill("#222b6d");
        op.colorize(20);
        op.gamma(0.5);
        op.contrast();
        op.contrast();
        op.addImage(input.getAbsolutePath());
        Operators.runCommand(op);
        new Effect(input).border();
    }
}
