package org.alancesar.darkroom.engine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.alancesar.darkroom.engine.editor.Effect;
import org.alancesar.darkroom.engine.editor.Image;
import org.alancesar.darkroom.engine.exif.Exif;
import org.alancesar.darkroom.engine.filter.Filter;
import org.alancesar.darkroom.engine.filter.FilterFactory;
import org.alancesar.darkroom.engine.filter.Filters;
import org.alancesar.darkroom.engine.util.ExifReader;

import com.drew.imaging.ImageProcessingException;

public class Darkroom {

    private static final double DEFAULT_COMPRESS_QUALITY = 20;
    private static ExifReader reader;
    private final File input;
    private File temp;
    private File output;

    public Darkroom(File input) {
        this.input = input;
        this.output = input;
    }

    public File getOutput() {
        return output;
    }

    public Darkroom setOutput(File output) {
        this.output = output;
        return this;
    }

    public Exif getExif() {
        try {
            if (reader == null) {
                reader = new ExifReader();
            }

            return reader.readFromFile(input);
        } catch (IOException | ImageProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void applyFilter(Filter filter) {
        createTempFile();
        filter.apply(temp);
        createOutput();
    }
    
    public void applyFilter(String filterName) {
        Filter filter = FilterFactory.getByName(filterName);
        
        if (filter != null) {
            applyFilter(filter);
        }
    }
    
    public void applyFilter(Filters filter) {
        applyFilter(FilterFactory.get(filter));
    }

    public void resize(int width, int height) {
        createTempFile();
        Effect.resize(temp, width, height);
        createOutput();
    }

    public void resize(int width) {
        createTempFile();
        Effect.resize(temp, width);
        createOutput();
    }

    public void limitSize(int width) {
        if (new Image(input).getWidth() > width) {
            resize(width);
        }
    }

    public void compress(double quality) {
        createTempFile();
        Effect.compress(temp, quality);
        createOutput();
    }

    public void compress() {
        compress(DEFAULT_COMPRESS_QUALITY);
    }

    private void createTempFile() {
        try {
            temp = File.createTempFile("darkroom", ".jpg");
            temp = Files.copy(input.toPath(), temp.toPath(), StandardCopyOption.COPY_ATTRIBUTES,
                    StandardCopyOption.REPLACE_EXISTING).toFile();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void createOutput() {
        try {
            output = Files.copy(temp.toPath(), output.toPath(), StandardCopyOption.COPY_ATTRIBUTES,
                    StandardCopyOption.REPLACE_EXISTING).toFile();
            temp.delete();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}