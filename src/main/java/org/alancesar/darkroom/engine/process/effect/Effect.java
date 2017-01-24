package org.alancesar.darkroom.engine.process.effect;

import java.io.File;

import org.alancesar.darkroom.engine.process.Operation;
import org.alancesar.darkroom.engine.process.Process;

public abstract class Effect implements Process {

    private Process next;
    private File output;

    @Override
    public final void execute(File input) {

        File output = this.output == null ? input : this.output;

        create(input, output).execute();

        if (next != null) {
            next.execute(output);
        }
    }

    @Override
    public final void output(File output) {
        this.output = output;
    }

    @Override
    public final void next(Process next) {
        this.next = next;
    }

    protected abstract Operation create(File input, File output);
}
