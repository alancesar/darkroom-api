package org.alancesar.darkroom.engine.filter;

import org.alancesar.darkroom.engine.editor.Effect;
import org.alancesar.darkroom.engine.editor.Image;
import org.alancesar.darkroom.engine.editor.Processor;
import org.im4java.core.IMOperation;

public class Lomo implements FilterEffect {
    @Override
    public void apply(Image image) {
        IMOperation op = new IMOperation();
        op.addImage(image.getFile().getAbsolutePath());
        op.channel("R");
        op.level();
        op.addRawArgs("33%");
        op.channel("G");
        op.level();
        op.addRawArgs("33%");
        op.addImage(image.getFile().getAbsolutePath());
        Processor.runCommand(op);
        new Effect(image).vignette();
    }
}
