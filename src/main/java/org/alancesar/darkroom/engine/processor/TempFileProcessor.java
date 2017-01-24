package org.alancesar.darkroom.engine.processor;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class TempFileProcessor implements Processor {

    private Processor next;

    @Override
    public void process(File input) {
        try {
            File temp = File.createTempFile("darkroom", ".jpg");
            temp = Files.copy(input.toPath(), temp.toPath(), StandardCopyOption.COPY_ATTRIBUTES,
                    StandardCopyOption.REPLACE_EXISTING).toFile();

            if (next != null) {
                next.process(temp);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void output(File output) {
        return;
    }

    @Override
    public void next(Processor next) {
        this.next = next;
    }

}
