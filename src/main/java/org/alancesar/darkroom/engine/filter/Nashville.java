package org.alancesar.darkroom.engine.filter;

import java.io.File;

import org.alancesar.darkroom.engine.editor.Effect;
import org.alancesar.darkroom.engine.editor.Processor;
import org.im4java.core.IMOperation;

public class Nashville implements Operation {

	@Override
	public void process(File file) {

		Effect effect = new Effect(file);
		effect.colorTone("#222b6d", 100, true);
		effect.colorTone("#f7daae", 100, false);

		IMOperation op = new IMOperation();
		op.addImage(file.getAbsolutePath());
		op.contrast();
		op.modulate(100d, 150d, 100d);
		op.autoGamma();
		op.addImage(file.getAbsolutePath());
		Processor.runCommand(op);
	}

}
