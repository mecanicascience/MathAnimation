package me.xam4lor.main;

import me.xam4lor.utils.Constants;
import me.xam4lor.utils.Record;
import processing.core.PApplet;

public class ProcessingMain extends PApplet {
	private static Record record;
	private static Logic logic;

	public void settings() {
		size(Constants.WIDTH, Constants.HEIGHT);
	}

	public void setup() {
		record = new Record(this);
		logic = new Logic(this);
	}
	
	public void draw() {
		background(10, 10, 10);
		
		logic.update();
		logic.draw();
		record.recordFrame();
	}
	
	public void keyPressed() {
		logic.keyPressed();
		record.keyPressed(key);
	}
}
