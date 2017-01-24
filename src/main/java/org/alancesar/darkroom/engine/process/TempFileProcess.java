package org.alancesar.darkroom.engine.process;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class TempFileProcess implements Process {

    private Process next;

    @Override
    public void execute(File input) {
        try {
            File temp = File.createTempFile("darkroom", ".jpg");
            temp = Files.copy(input.toPath(), temp.toPath(), StandardCopyOption.COPY_ATTRIBUTES,
                    StandardCopyOption.REPLACE_EXISTING).toFile();

            if (next != null) {
                next.execute(temp);
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
    public void next(Process next) {
        this.next = next;
    }

}
