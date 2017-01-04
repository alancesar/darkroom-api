package org.alancesar.darkroom.engine.filter;

import java.io.File;

import org.alancesar.darkroom.engine.editor.Effect;
import org.alancesar.darkroom.engine.editor.Processor;
import org.im4java.core.IMOperation;

public class Nashville implements Filter {

	@Override
	public void apply(File input) {

		Effect.colorTone(input, "#222b6d", 100, true);
		Effect.colorTone(input, "#f7daae", 100, false);

		IMOperation op = new IMOperation();
		op.addImage(input.getAbsolutePath());
		op.contrast();
		op.modulate(100d, 150d, 100d);
		op.autoGamma();
		op.addImage(input.getAbsolutePath());
		Processor.runCommand(op);
	}

}
