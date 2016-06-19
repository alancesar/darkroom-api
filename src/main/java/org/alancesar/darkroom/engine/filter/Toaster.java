package org.alancesar.darkroom.engine.filter;

import org.alancesar.darkroom.engine.editor.Effect;
import org.alancesar.darkroom.engine.editor.Image;
import org.alancesar.darkroom.engine.editor.Processor;
import org.im4java.core.IMOperation;

public class Toaster implements Operation {

	@Override
	public void process(Image image) {
		Effect effect = new Effect(image);
        effect.colorTone("#330000", 100, true);

        IMOperation op = new IMOperation();
        op.addImage(image.getFile().getAbsolutePath());
        op.modulate(150d, 80d, 100d);
        op.gamma(1.2);
        op.contrast();
        op.contrast();
        op.addImage(image.getFile().getAbsolutePath());
        Processor.runCommand(op);

        effect.vignette("none", "LavenderBlush3");
        effect.vignette("#ff9966", "none");
	}

}
