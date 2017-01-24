package org.alancesar.darkroom.engine.effect;

import java.io.File;

import org.alancesar.darkroom.engine.processor.Operation;
import org.alancesar.darkroom.engine.processor.Processor;

public abstract class Effect implements Processor {

    private Processor next;
    private File output;

    @Override
    public final void process(File input) {

        File output = this.output == null ? input : this.output;

        create(input, output).execute();

        if (next != null) {
            next.process(output);
        }
    }

    @Override
    public final void output(File output) {
        this.output = output;
    }

    @Override
    public final void next(Processor next) {
        this.next = next;
    }

    protected abstract Operation create(File input, File output);
}
