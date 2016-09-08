package org.alancesar.darkroom.engine.filter;

import java.io.File;

import org.alancesar.darkroom.engine.editor.Effect;
import org.alancesar.darkroom.engine.editor.Image;
import org.alancesar.darkroom.engine.editor.Processor;
import org.im4java.core.IMOperation;

public class Gotham implements Operation {

	@Override
	public void process(File file) {

		Image image = new Image(file);

		int border = (int) (image.getWidth() * 0.05);

		IMOperation op = new IMOperation();
		op.addImage(file.getAbsolutePath());
		op.modulate(120d, 10d, 100d);
		op.fill("#222b6d");
		op.colorize(20);
		op.gamma(0.5);
		op.contrast();
		op.contrast();
		op.shave(border);
		op.addImage(file.getAbsolutePath());
		Processor.runCommand(op);

		new Effect(file).border(border);
	}
}
