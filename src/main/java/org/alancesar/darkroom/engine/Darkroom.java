package org.alancesar.darkroom.engine;

import com.drew.imaging.ImageProcessingException;

import org.alancesar.darkroom.engine.editor.Effect;
import org.alancesar.darkroom.engine.editor.Image;
import org.alancesar.darkroom.engine.exif.Photo;
import org.alancesar.darkroom.engine.filter.Filter;
import org.alancesar.darkroom.engine.util.ExifReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Random;

public class Darkroom {
	private static final double DEFAULT_COMPRESS_QUALITY = 20;
	
    private final Image input;
    private Image temp;
    private File output;

    public Darkroom(File input) {
        this.input = new Image(input);
        this.output = input;
    }

    public File getOutput() {
        return output;
    }

    public Darkroom setOutput(File output) {
        this.output = output;
        return this;
    }

    public Photo getExif() {
        try {
        	return new ExifReader().readFromFile(input.getFile());
        } catch (IOException | ImageProcessingException e) {
        	throw new RuntimeException(e);
		}
    }

    public File applyFilter(Filter filter) {
        createTempFile();
        filter.apply(temp);
        return createOutput();
    }

    public File resize(int width, int height) {
        createTempFile();
        new Effect(temp).resize(width, height);
        return createOutput();
    }

    public File resize(int width) {
    	createTempFile();
        new Effect(temp).resize(width);
        return createOutput();
    }
    
    public File limitSize(int width) {
    	if (input.getWidth() > width)
    		return resize(width);
    	
    	return input.getFile();
    }

    public File compress(double quality) {
        createTempFile();
        new Effect(temp).compress(quality);
        return createOutput();
    }
    
    public File compress() {
    	return compress(DEFAULT_COMPRESS_QUALITY);
    }

    private void createTempFile() {
        try {
            Path from = Paths.get(input.getFile().getAbsolutePath());
            Path to = Paths.get(input.getFile().getParent(), Long.toString(new Random().nextInt(9999)));
            temp = new Image(Files.copy(from, to).toFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private File createOutput() {
        Path from = Paths.get(temp.getFile().getAbsolutePath());
        Path to = Paths.get(output.getParent(), output.getName());
        
        try {
	        if (!Files.exists(to.getParent()))
	            Files.createDirectories(to.getParent());
	        
	        File output = Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING).toFile();
	        Files.delete(Paths.get(temp.getFile().getAbsolutePath()));
	        return output;
        } catch (IOException  e) {
        	throw new RuntimeException(e);
		}
    }
}