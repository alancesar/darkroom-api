package org.alancesar.darkroom.engine.filter;

import java.io.File;

import org.alancesar.darkroom.engine.editor.Processor;
import org.im4java.core.IMOperation;

public class Normalize implements Filter {

	@Override
	public void apply(File input) {

		IMOperation op = new IMOperation();
		op.addImage(input.getAbsolutePath());
		op.normalize();
		op.contrast();
		op.contrast();
		op.addImage(input.getAbsolutePath());
		Processor.runCommand(op);
	}

}
