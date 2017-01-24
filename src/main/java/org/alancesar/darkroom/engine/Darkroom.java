package org.alancesar.darkroom.engine;

import java.io.File;
import java.io.IOException;

import org.alancesar.darkroom.engine.exif.Exif;
import org.alancesar.darkroom.engine.exif.ExifReader;
import org.alancesar.darkroom.engine.filter.Filter;
import org.alancesar.darkroom.engine.filter.Filters;
import org.alancesar.darkroom.engine.model.Image;
import org.alancesar.darkroom.engine.process.OutputFileProcess;
import org.alancesar.darkroom.engine.process.Process;
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
        Process tempFileProcess = new TempFileProcess();
        Process effectProcess = filter.effect();
        Process outputFileProcess = new OutputFileProcess(output);

        tempFileProcess.next(effectProcess);
        effectProcess.next(outputFileProcess);

        tempFileProcess.execute(input);
    }

    public void applyFilter(String filterName) {
        Filter filter = Filters.getByName(filterName);

        if (filter != null) {
            applyFilter(filter);
        }
    }

    public void resize(int width, int height) {
        Process tempFileProcess = new TempFileProcess();
        Process effectProcess = new ResizeEffect(width, height);
        Process outputFileProcess = new OutputFileProcess(output);

        tempFileProcess.next(effectProcess);
        effectProcess.next(outputFileProcess);

        tempFileProcess.execute(input);
    }

    public void resize(int width) {
        Process tempFileProcess = new TempFileProcess();
        Process effectProcess = new ResizeEffect(width);
        Process outputFileProcess = new OutputFileProcess(output);

        tempFileProcess.next(effectProcess);
        effectProcess.next(outputFileProcess);

        tempFileProcess.execute(input);
    }

    public void limitSize(int maxWidth) {
        if (new Image(input).getWidth() > maxWidth) {
            Process tempFileProcess = new TempFileProcess();
            Process effectProcess = new ResizeEffect(maxWidth);
            Process outputFileProcess = new OutputFileProcess(output);

            tempFileProcess.next(effectProcess);
            effectProcess.next(outputFileProcess);

            tempFileProcess.execute(input);
        }
    }

    public void compress(double quality) {
        Process tempFileProcess = new TempFileProcess();
        Process effectProcess = new CompressEffect(quality);
        Process outputFileProcess = new OutputFileProcess(output);

        tempFileProcess.next(effectProcess);
        effectProcess.next(outputFileProcess);

        tempFileProcess.execute(input);
    }

    public void compress() {
        Process tempFileProcess = new TempFileProcess();
        Process effectProcess = new CompressEffect();
        Process outputFileProcess = new OutputFileProcess(output);

        tempFileProcess.next(effectProcess);
        effectProcess.next(outputFileProcess);

        tempFileProcess.execute(input);
    }
}