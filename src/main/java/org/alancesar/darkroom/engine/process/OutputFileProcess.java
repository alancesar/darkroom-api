package org.alancesar.darkroom.engine.process;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class OutputFileProcess implements Process {

    private File output;

    public OutputFileProcess(File output) {
        this.output = output;
    }

    @Override
    public File execute(File input) {
        try {
            output = Files.copy(input.toPath(), output.toPath(), StandardCopyOption.COPY_ATTRIBUTES,
                    StandardCopyOption.REPLACE_EXISTING).toFile();
            input.delete();

            return output;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
