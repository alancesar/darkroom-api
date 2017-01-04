package org.alancesar.darkroom.engine.filter;

import java.io.File;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;

@FunctionalInterface
public interface Filter {

    void apply(File input);

    default void runCommand(IMOperation op) {
        try {
            new ConvertCmd().run(op);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
