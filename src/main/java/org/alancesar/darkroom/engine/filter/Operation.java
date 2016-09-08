package org.alancesar.darkroom.engine.filter;

import java.io.File;

@FunctionalInterface
public interface Operation {

	void process(File file);
}
