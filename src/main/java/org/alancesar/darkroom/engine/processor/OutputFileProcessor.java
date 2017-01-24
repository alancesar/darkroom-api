package org.alancesar.darkroom.engine.processor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class OutputFileProcessor implements Processor {

    private Processor next;
    private File output;
    
    public OutputFileProcessor(File output) {
        this.output = output;
    }

    @Override
    public void process(File input) {
        try {
            output = Files.copy(input.toPath(), output.toPath(), StandardCopyOption.COPY_ATTRIBUTES,
                    StandardCopyOption.REPLACE_EXISTING).toFile();
            input.delete();

            if (next != null) {
                next.process(output);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void output(File output) {
        this.output = output;
    }

    @Override
    public void next(Processor next) {
        this.next = next;
    }

}
