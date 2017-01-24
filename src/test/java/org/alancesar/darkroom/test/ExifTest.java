package org.alancesar.darkroom.test;

import java.io.File;

import org.alancesar.darkroom.engine.Darkroom;
import org.alancesar.darkroom.engine.exif.Exif;
import org.junit.Test;

public class ExifTest {
    
    @Test
    public void getExif() {
        
        ClassLoader classLoader = getClass().getClassLoader();
        File input = new File(classLoader.getResource("chain.jpg").getFile());
        
        Darkroom darkroom = new Darkroom(input);
        Exif exif = darkroom.getExif();
        
        System.out.println(exif);
    }

}
