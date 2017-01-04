package org.alancesar.darkroom.engine.filter;

import java.io.File;

@FunctionalInterface
public interface Filter {

	void apply(File input);
}
