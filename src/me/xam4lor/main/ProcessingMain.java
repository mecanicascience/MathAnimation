package me.xam4lor.main;

import me.xam4lor.utils.Constants;
import me.xam4lor.utils.Record;
import processing.core.PApplet;

/**
 * Processing Instance
 */
public class ProcessingMain extends PApplet {
	private static Record record;
	private static Logic logic;

	
	@Override
	public void settings() {
		size(Constants.WIDTH, Constants.HEIGHT);
	}

	
	@Override
	public void setup() {
		record = new Record(this);
		logic = new Logic(this);
	}
	
	
	@Override
	public void draw() {
		background(10, 10, 10);
		
		logic.update();
		logic.draw();
		record.recordFrame();
	}
	
	@Override
	public void keyPressed() {
		logic.keyPressed();
		record.keyPressed(key);
	}
}
