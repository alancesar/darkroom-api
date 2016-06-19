package org.alancesar.darkroom.engine.editor;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Image {
	
	private File file;
	private int width;
	private int height;
	
	public Image(File file) {
		this.file = file;
		
		try {
			BufferedImage bufferedImage = ImageIO.read(file);
			width = bufferedImage.getWidth();
			height = bufferedImage.getHeight();			
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	public File getFile() {
		return file;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
