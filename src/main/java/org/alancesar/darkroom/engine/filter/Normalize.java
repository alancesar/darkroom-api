package org.alancesar.darkroom.engine.filter;

import java.io.File;

import org.alancesar.darkroom.engine.editor.Processor;
import org.im4java.core.IMOperation;

public class Normalize implements Operation {

	@Override
	public void process(File file) {

		IMOperation op = new IMOperation();
		op.addImage(file.getAbsolutePath());
		op.normalize();
		op.contrast();
		op.contrast();
		op.addImage(file.getAbsolutePath());
		Processor.runCommand(op);
	}

}
