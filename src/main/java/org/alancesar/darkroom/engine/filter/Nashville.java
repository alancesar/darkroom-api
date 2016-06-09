package org.alancesar.darkroom.engine.filter;

import org.alancesar.darkroom.engine.editor.Effect;
import org.alancesar.darkroom.engine.editor.Operators;
import org.im4java.core.IMOperation;

import java.io.File;

public class Nashville implements FilterEffect {
    @Override
    public void apply(File input) {
        Effect effect = new Effect(input);
        effect.colorTone("#222b6d", 100, true);
        effect.colorTone("#f7daae", 100, false);

        IMOperation op = new IMOperation();
        op.addImage(input.getAbsolutePath());
        op.contrast();
        op.modulate(100d, 150d, 100d);
        op.autoGamma();
        op.addImage(input.getAbsolutePath());
        Operators.runCommand(op);
    }
}
