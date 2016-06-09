package org.alancesar.darkroom.engine.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.alancesar.darkroom.engine.Darkroom;
import org.alancesar.darkroom.engine.filter.Filter;
import org.junit.Test;

import com.drew.imaging.ImageProcessingException;

public class FilterTest {
	
	@Test
	public void filters() throws IOException, ImageProcessingException {		
		File temp = File.createTempFile(String.valueOf(System.currentTimeMillis()), ".jpg");
		String url = "https://c2.staticflickr.com/8/7096/26574598254_f851221e04_c.jpg";
		BufferedImage image = ImageIO.read(new URL(url));
		ImageIO.write(image, "jpg", temp);
		Darkroom darkroom = new Darkroom(temp);
		darkroom.setOutput(new File(System.getProperty("user.home") + "/darkroom_output.jpg"));
		darkroom.applyFilter(Filter.KELVIN);
	}
}
