package org.alancesar.darkroom.engine.filter;

import java.io.File;

import org.alancesar.darkroom.engine.editor.Effect;
import org.alancesar.darkroom.engine.editor.Processor;
import org.im4java.core.IMOperation;

public class Lomo implements Operation {

	@Override
	public void process(File file) {

		IMOperation op = new IMOperation();
		op.addImage(file.getAbsolutePath());
		op.channel("R");
		op.level();
		op.addRawArgs("33%");
		op.channel("G");
		op.level();
		op.addRawArgs("33%");
		op.addImage(file.getAbsolutePath());
		Processor.runCommand(op);
		new Effect(file).vignette();
	}

}
