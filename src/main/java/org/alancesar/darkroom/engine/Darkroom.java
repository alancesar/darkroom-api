package org.alancesar.darkroom.engine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import org.alancesar.darkroom.engine.editor.Effect;
import org.alancesar.darkroom.engine.editor.Image;
import org.alancesar.darkroom.engine.exif.PhotoExif;
import org.alancesar.darkroom.engine.filter.Filter;
import org.alancesar.darkroom.engine.util.ExifReader;
import org.alancesar.util.FileUtils;

import com.drew.imaging.ImageProcessingException;

public class Darkroom {

	private final File input;
	private File temp;
	private File output;

	private static ExifReader reader;

	private static final double DEFAULT_COMPRESS_QUALITY = 20;
	private static Random random = new Random();

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

	public PhotoExif getExif() {

		try {
			if (reader == null) {
				reader = new ExifReader();
			}

			return reader.readFromFile(input);
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

		if (new Image(input).getWidth() > width) {
			return resize(width);
		}

		return input;
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
			String filename = randomFilename();

			Path from = Paths.get(input.getAbsolutePath());
			Path to = Paths.get(input.getParent(), filename);

			temp = Files.copy(from, to).toFile();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private File createOutput() {

		try {
			return FileUtils.moveFile(temp, output.getAbsolutePath());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private String randomFilename() {

		return Long.toString(random.nextInt(9999));
	}
}