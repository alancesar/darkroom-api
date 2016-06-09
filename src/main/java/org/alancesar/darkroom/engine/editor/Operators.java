package org.alancesar.darkroom.engine.editor;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;
import org.im4java.core.Info;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public abstract class Operators {

    public static void runCommand(IMOperation op) {
        ConvertCmd cmd = new ConvertCmd();

        try {
            cmd.run(op);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Integer> getImageSize(File input) {
        Map<String, Integer> size = new HashMap<>();

        try {
            Info info = new Info(input.getAbsolutePath());
            size.put("width", info.getImageWidth());
            size.put("height", info.getImageHeight());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return size;
    }
}
