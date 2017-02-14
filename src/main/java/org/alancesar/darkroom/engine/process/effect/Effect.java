package org.alancesar.darkroom.engine.process.effect;

import java.io.File;

import org.alancesar.darkroom.engine.process.Operation;
import org.alancesar.darkroom.engine.process.Process;

public abstract class Effect implements Process {

    @Override
    public final File execute(File input) {
        create(input, input).execute();
        return input;
    }

    public abstract Operation create(File input, File output);
}
