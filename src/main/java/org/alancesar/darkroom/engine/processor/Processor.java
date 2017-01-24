package org.alancesar.darkroom.engine.processor;

import java.io.File;

public interface Processor {

    void process(File input);

    void output(File output);

    void next(Processor next);

}
