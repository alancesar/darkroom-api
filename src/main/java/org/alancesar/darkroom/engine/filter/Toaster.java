package org.alancesar.darkroom.engine.filter;

import java.io.File;

import org.alancesar.darkroom.engine.editor.Effect;
import org.im4java.core.IMOperation;

public class Toaster implements Filter {

    @Override
    public void apply(File input) {

        Effect.colorTone(input, "#330000", 100, true);

        IMOperation op = new IMOperation();
        op.addImage(input.getAbsolutePath());
        op.modulate(150d, 80d, 100d);
        op.gamma(1.2);
        op.contrast();
        op.contrast();
        op.addImage(input.getAbsolutePath());
        runCommand(op);

        Effect.vignette(input, "none", "LavenderBlush3");
        Effect.vignette(input, "#ff9966", "none");
    }

}
