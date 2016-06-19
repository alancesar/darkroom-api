package org.alancesar.darkroom.engine;

import com.drew.imaging.ImageProcessingException;

import org.alancesar.darkroom.engine.editor.Image;
import org.alancesar.darkroom.engine.editor.Processor;
import org.alancesar.darkroom.engine.exif.Photo;
import org.alancesar.darkroom.engine.filter.Filter;
import org.alancesar.darkroom.engine.util.ExifReader;
import org.im4java.core.IMOperation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Random;

public class Darkroom {
	private static final double DEFAULT_COMPRESS_QUALITY = 20;
	private static final int MINIMUM_HEIGHT = 20;
	
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
        filter.get().apply(temp);
        return createOutput();
    }

    public File resize(int width, int height) {
        createTempFile();
        IMOperation op = new IMOperation();
        op.addImage(temp.getFile().getAbsolutePath());

        if (height < MINIMUM_HEIGHT)
        	op.resize(width);
        else
        	op.resize(width, height);

        op.unsharp(1.5, 1.0, 1.5, 0.02);
        op.addImage(temp.getFile().getAbsolutePath());
        Processor.runCommand(op);
        return createOutput();
    }

    public File resize(int width) {
        return resize(width, 0);
    }
    
    public File limitSize(int width) {
    	if (input.getWidth() > width)
    		return resize(width);
    	
    	return input.getFile();
    }

    public File compress(double quality) {
    	if (quality < 0.0 || quality > 100.0)
    		quality = DEFAULT_COMPRESS_QUALITY;
    	
        createTempFile();
        IMOperation op = new IMOperation();
        op.addImage(temp.getFile().getAbsolutePath());
        op.strip();
        op.interlace("Plane");
        op.gaussianBlur(0.05);
        op.quality(quality);
        op.addImage(temp.getFile().getAbsolutePath());
        Processor.runCommand(op);
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