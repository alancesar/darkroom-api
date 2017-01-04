package org.alancesar.darkroom.engine.filter;

import java.io.File;

import org.alancesar.darkroom.engine.editor.Effect;
import org.im4java.core.IMOperation;

public class Lomo implements Filter {

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
        runCommand(op);
        Effect.vignette(input);
    }

}
