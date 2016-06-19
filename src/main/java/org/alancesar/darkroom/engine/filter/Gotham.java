package org.alancesar.darkroom.engine.filter;

import org.alancesar.darkroom.engine.editor.Effect;
import org.alancesar.darkroom.engine.editor.Image;
import org.alancesar.darkroom.engine.editor.Processor;
import org.im4java.core.IMOperation;

public class Gotham implements FilterEffect {
    @Override
    public void apply(Image image) {
        IMOperation op = new IMOperation();
        op.addImage(image.getFile().getAbsolutePath());
        op.modulate(120d, 10d, 100d);
        op.fill("#222b6d");
        op.colorize(20);
        op.gamma(0.5);
        op.contrast();
        op.contrast();
        op.addImage(image.getFile().getAbsolutePath());
        Processor.runCommand(op);
        new Effect(image).border();
    }
}
