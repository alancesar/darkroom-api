package org.alancesar.darkroom.engine.process;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class TempFileProcess implements Process {

    @Override
    public File execute(File input) {
        try {
            File temp = File.createTempFile("darkroom", ".jpg");
            temp = Files.copy(input.toPath(), temp.toPath(), StandardCopyOption.COPY_ATTRIBUTES,
                    StandardCopyOption.REPLACE_EXISTING).toFile();
            return temp;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
