package org.alancesar.darkroom.engine;

import java.io.File;
import java.io.IOException;

import org.alancesar.darkroom.engine.exif.Exif;
import org.alancesar.darkroom.engine.exif.ExifReader;
import org.alancesar.darkroom.engine.filter.Filter;
import org.alancesar.darkroom.engine.filter.Filters;
import org.alancesar.darkroom.engine.model.Image;
import org.alancesar.darkroom.engine.process.OutputFileProcess;
import org.alancesar.darkroom.engine.process.Processor;
import org.alancesar.darkroom.engine.process.TempFileProcess;
import org.alancesar.darkroom.engine.process.effect.CompressEffect;
import org.alancesar.darkroom.engine.process.effect.ResizeEffect;

public class Darkroom {

    private final File input;
    private File output;

    public Darkroom(File input) {
        this.input = input;
        this.output = input;
    }

    public File getOutput() {
        return output;
    }

    public void setOutput(File output) {
        this.output = output;
    }

    public Exif getExif() {
        try {
            return ExifReader.getInstance().readFromFile(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void applyFilter(Filter filter) {
        Processor processor = new Processor(new TempFileProcess()).andThen(filter.effect())
                .andThen(new OutputFileProcess(output));

        processor.execute(input);
    }

    public void applyFilter(String filterName) {
        Filter filter = Filters.getByName(filterName);

        if (filter != null) {
            applyFilter(filter);
        }
    }

    public void resize(int width, int height) {
        Processor processor = new Processor(new TempFileProcess()).andThen(new ResizeEffect(width, height))
                .andThen(new OutputFileProcess(output));

        processor.execute(input);
    }

    public void resize(int width) {
        Processor processor = new Processor(new TempFileProcess()).andThen(new ResizeEffect(width))
                .andThen(new OutputFileProcess(output));

        processor.execute(input);
    }

    public void limitSize(int maxWidth) {
        if (new Image(input).getWidth() > maxWidth) {
            resize(maxWidth);
        }
    }

    public void compress(double quality) {
        Processor processor = new Processor(new TempFileProcess()).andThen(new CompressEffect(quality))
                .andThen(new OutputFileProcess(output));

        processor.execute(input);
    }

    public void compress() {
        Processor processor = new Processor(new TempFileProcess()).andThen(new CompressEffect())
                .andThen(new OutputFileProcess(output));

        processor.execute(input);
    }
}