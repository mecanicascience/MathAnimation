package me.xam4lor.utils;

import com.hamoid.VideoExport;

import processing.core.PApplet;

/**
 * Video exporter class
 */
public class Record {
	private PApplet applet;
	
	private VideoExport videoExport;
	private boolean isRecording;
	
	private int videoIndex;
	
	
	
	public Record(PApplet applet) {
		this.applet = applet;
		
		videoIndex = 0;
		
		videoExport = new VideoExport(applet, Constants.VIDEO_NAME + this.videoIndex + "." + Constants.VIDEO_EXTENSION);
		videoExport.setFrameRate(60); // export 60 FPS
		videoExport.setQuality(85, 192); // (100-85)=15% compression and 192kbps
	}
	
	/**
	 * Record current frame
	 */
	public void recordFrame() {
		if(isRecording) {
			videoExport.saveFrame();
			applet.fill(0, 255, 0);
		}
		else {
			applet.fill(255, 0, 0);
		}
		
		applet.noStroke();
		applet.ellipse(8, 8, 8, 8);
	}
	
	
	
	/**
	 * Start recording
	 */
	public void startRecording() {
		if(this.isRecording) {
			System.out.println("/!\\ INFO : Software is already recording.");
		}
		else {
			videoExport.startMovie();
			
			this.videoIndex++;
			this.isRecording = true;
			
			System.out.println("/!\\ INFO : ===== Recording =====");
		}
		
		
	}
	
	/**
	 * Stop recording
	 */
	public void stopRecording() {
		if(!this.isRecording) {
			System.out.println("/!\\ INFO : Software is not recording.");
		}
		else {
			videoExport.setMovieFileName(Constants.VIDEO_NAME + this.videoIndex + "." + Constants.VIDEO_EXTENSION);
			videoExport.endMovie();
			
			this.isRecording = false;
			
			System.out.println("/!\\ INFO : ===== End recording =====");

		}
	}

	/**
	 * On key Pressed
	 * @param key
	 */
	public void keyPressed(char key) {
		if(key == Constants.KEY_START_RECORDING) {
			this.startRecording();
		}
		else if(key == Constants.KEY_END_RECORDING) {
			this.stopRecording();
		}
	}
}
