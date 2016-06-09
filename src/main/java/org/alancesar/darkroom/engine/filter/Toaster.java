package org.alancesar.darkroom.engine.filter;

import org.alancesar.darkroom.engine.editor.Effect;
import org.alancesar.darkroom.engine.editor.Operators;
import org.im4java.core.IMOperation;

import java.io.File;

public class Toaster implements FilterEffect {
    @Override
    public void apply(File input) {
        Effect effect = new Effect(input);
        effect.colorTone("#330000", 100, true);

        IMOperation op = new IMOperation();
        op.addImage(input.getAbsolutePath());
        op.modulate(150d, 80d, 100d);
        op.gamma(1.2);
        op.contrast();
        op.contrast();
        op.addImage(input.getAbsolutePath());
        Operators.runCommand(op);

        effect.vignette("none", "LavenderBlush3");
        effect.vignette("#ff9966", "none");
    }
}
