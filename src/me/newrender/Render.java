package me.newrender;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Render {
	public void render() {
		i = 0;
		
		BufferedImage bf1 = getBufferedImage1();
		BufferedImage bf2 = getBufferedImage2();
		
		for (int i = 0; i < 60 * 3; i++) {
			exportImage(bf1);
		}
		for (int i = 0; i < 60 * 3; i++) {
			exportImage(bf2);
		}
	}
	
	private BufferedImage getBufferedImage1() {
		int width = 200, height = 200;
		
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D ig2 = bi.createGraphics();
		
		Font font = new Font("TimesRoman", Font.BOLD, 20);
		ig2.setFont(font);
		
		String message = "www.java2s.com!";
		FontMetrics fontMetrics = ig2.getFontMetrics();
		
		int stringWidth = fontMetrics.stringWidth(message);
		int stringHeight = fontMetrics.getAscent();
		
		ig2.setPaint(Color.black);
		ig2.drawString(message, (width - stringWidth) / 2, height / 2 + stringHeight / 4);
		
		return bi;
	}
	
	private BufferedImage getBufferedImage2() {
		int width = 200, height = 200;
		
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D ig2 = bi.createGraphics();
		
		Font font = new Font("TimesRoman", Font.BOLD, 20);
		ig2.setFont(font);
		
		String message = "www.java2s.com! - 2nd version";
		FontMetrics fontMetrics = ig2.getFontMetrics();
		
		int stringWidth = fontMetrics.stringWidth(message);
		int stringHeight = fontMetrics.getAscent();
		
		ig2.setPaint(Color.black);
		ig2.drawString(message, (width - stringWidth) / 2, height / 2 + stringHeight / 4);
		
		return bi;
	}
	
	
	private int i;
	private void exportImage(BufferedImage bf) {
		try {
			ImageIO.write(bf, "png", new File("tmp/" + i + ".png"));
			i++;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
