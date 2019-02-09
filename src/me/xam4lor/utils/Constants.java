package me.xam4lor.utils;

import processing.core.PFont;

/**
 * List of the project constant values
 */
public final class Constants {
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 1000;
	
	public static final char KEY_START_RECORDING = 's';
	public static final char KEY_END_RECORDING = 'q';
	
	public static final String VIDEO_NAME = "processing_output_";
	public static final String VIDEO_EXTENSION = "mp4";
	
	public static final float VEC_ANGLE_ARROW = 0.6f;
	public static final float VEC_SIZE_ARROW = 10f;
	
	public static PFont mainFont;
	
	
	private Constants() {
		throw new AssertionError();
	}
}
