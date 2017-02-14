package org.alancesar.darkroom.test;

import java.io.File;

import org.alancesar.darkroom.engine.Darkroom;
import org.junit.Assert;
import org.junit.Test;

public class FilterTest {

    @Test
    public void lomoFilter() {
        ClassLoader classLoader = getClass().getClassLoader();
        File input = new File(classLoader.getResource("mushrooms.jpg").getFile());
        File output = new File(classLoader.getResource("").getFile(), "output.jpg");

        if (output.exists()) {
            output.delete();
        }

        Darkroom darkroom = new Darkroom(input);
        darkroom.setOutput(output);
        darkroom.applyFilter("lomo");

        Assert.assertTrue(output.exists());
    }

}
