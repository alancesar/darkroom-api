package org.alancesar.darkroom.engine.process;

import java.io.File;

public interface Process {

    void execute(File input);

    void output(File output);

    void next(Process next);

}
