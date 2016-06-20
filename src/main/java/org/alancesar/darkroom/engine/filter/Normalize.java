package org.alancesar.darkroom.engine.filter;

import org.alancesar.darkroom.engine.editor.Image;
import org.alancesar.darkroom.engine.editor.Processor;
import org.im4java.core.IMOperation;

public class Normalize implements Operation {

	@Override
	public void process(Image image) {
		IMOperation op = new IMOperation();
		op.addImage(image.getFile().getAbsolutePath());
		op.normalize();
		op.contrast();
		op.contrast();
		op.addImage(image.getFile().getAbsolutePath());
		Processor.runCommand(op);
	}

}
