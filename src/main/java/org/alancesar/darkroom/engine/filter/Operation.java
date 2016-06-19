package org.alancesar.darkroom.engine.filter;

import org.alancesar.darkroom.engine.editor.Image;

@FunctionalInterface
public interface Operation {
	void process(Image image);
}
