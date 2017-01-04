package org.alancesar.darkroom.engine.filter;

import java.io.File;

import org.alancesar.darkroom.engine.editor.Image;
import org.im4java.core.IMOperation;

public class Kelvin implements Filter {

	@Override
	public void apply(File input) {

		Image image = new Image(input);
		int width = image.getWidth();
		int height = image.getHeight();

		IMOperation sub1 = new IMOperation();
		sub1.addImage(input.getAbsolutePath());
		sub1.autoGamma();
		sub1.modulate(120d, 50d, 100d);

		IMOperation sub2 = new IMOperation();
		sub2.size(width, height);
		sub2.fill("rgba(255, 153, 0, 0.5");
		sub2.draw("rectangle 0, 0 " + width + ", " + height);

		IMOperation op = new IMOperation();
		op.addSubOperation(sub1);
		op.addSubOperation(sub2);
		op.compose("multiply");
		op.addImage(input.getAbsolutePath());

		runCommand(op);
	}

}
