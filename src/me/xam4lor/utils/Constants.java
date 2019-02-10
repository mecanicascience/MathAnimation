package me.xam4lor.utils;

import processing.core.PFont;

/**
 * List of the project constant values
 */
public final class Constants {
	/** Screen Width */
	public static final int WIDTH = 1200;
	/** Screen Height */
	public static final int HEIGHT = 1000;
	
	
	/** Key to begin recording */
	public static final char KEY_START_RECORDING = 's';
	/** Key to end recording */
	public static final char KEY_END_RECORDING = 'q';
	
	/** Video file name */
	public static final String VIDEO_NAME = "processing_output_";
	/** Video file extension (mp4 is a good option) */
	public static final String VIDEO_EXTENSION = "mp4";
	
	
	/** Size of the Vector arrow */
	public static final float VEC_SIZE_ARROW = 10f;
	
	
	/** Time on the initialisation of the program */
	public static final double initTimeSeconds = System.currentTimeMillis() / 1000;
	
	
	/** MainFont of the text */
	public static PFont mainFont;
}
