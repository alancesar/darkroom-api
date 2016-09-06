package org.alancesar.darkroom.engine.editor;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;

public abstract class Processor {

	public static void runCommand(IMOperation op) {
		try {
			new ConvertCmd().run(op);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
