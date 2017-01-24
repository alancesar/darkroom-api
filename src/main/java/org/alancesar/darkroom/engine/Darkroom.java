package org.alancesar.darkroom.engine;

import java.io.File;
import java.io.IOException;

import org.alancesar.darkroom.engine.effect.CompressEffect;
import org.alancesar.darkroom.engine.effect.ResizeEffect;
import org.alancesar.darkroom.engine.exif.Exif;
import org.alancesar.darkroom.engine.exif.ExifReader;
import org.alancesar.darkroom.engine.filter.Filter;
import org.alancesar.darkroom.engine.filter.Filters;
import org.alancesar.darkroom.engine.model.Image;
import org.alancesar.darkroom.engine.processor.OutputFileProcessor;
import org.alancesar.darkroom.engine.processor.Processor;
import org.alancesar.darkroom.engine.processor.TempFileProcessor;

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

    public Darkroom setOutput(File output) {
        this.output = output;
        return this;
    }

    public Exif getExif() {
        try {
            return ExifReader.getInstance().readFromFile(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void applyFilter(Filter filter) {
        Processor tempFileProcessor = new TempFileProcessor();
        Processor effectProcessor = filter.effect();
        Processor outputFileProcessor = new OutputFileProcessor(output);

        tempFileProcessor.next(effectProcessor);
        effectProcessor.next(outputFileProcessor);

        tempFileProcessor.process(input);
    }

    public void applyFilter(String filterName) {
        Filter filter = Filters.getByName(filterName);

        if (filter != null) {
            applyFilter(filter);
        }
    }

    public void resize(int width, int height) {
        Processor tempFileProcessor = new TempFileProcessor();
        Processor effectProcessor = new ResizeEffect(width, height);
        Processor outputFileProcessor = new OutputFileProcessor(output);

        tempFileProcessor.next(effectProcessor);
        effectProcessor.next(outputFileProcessor);

        tempFileProcessor.process(input);
    }

    public void resize(int width) {
        Processor tempFileProcessor = new TempFileProcessor();
        Processor effectProcessor = new ResizeEffect(width);
        Processor outputFileProcessor = new OutputFileProcessor(output);

        tempFileProcessor.next(effectProcessor);
        effectProcessor.next(outputFileProcessor);

        tempFileProcessor.process(input);
    }

    public void limitSize(int maxWidth) {
        if (new Image(input).getWidth() > maxWidth) {
            Processor tempFileProcessor = new TempFileProcessor();
            Processor effectProcessor = new ResizeEffect(maxWidth);
            Processor outputFileProcessor = new OutputFileProcessor(output);

            tempFileProcessor.next(effectProcessor);
            effectProcessor.next(outputFileProcessor);

            tempFileProcessor.process(input);
        }
    }

    public void compress(double quality) {
        Processor tempFileProcessor = new TempFileProcessor();
        Processor effectProcessor = new CompressEffect(quality);
        Processor outputFileProcessor = new OutputFileProcessor(output);

        tempFileProcessor.next(effectProcessor);
        effectProcessor.next(outputFileProcessor);

        tempFileProcessor.process(input);
    }

    public void compress() {
        Processor tempFileProcessor = new TempFileProcessor();
        Processor effectProcessor = new CompressEffect();
        Processor outputFileProcessor = new OutputFileProcessor(output);

        tempFileProcessor.next(effectProcessor);
        effectProcessor.next(outputFileProcessor);

        tempFileProcessor.process(input);
    }
}