package me.xam4lor.main;

import me.xam4lor.graph.FunctionPlot;
import me.xam4lor.graph.Plot;
import me.xam4lor.graph.ScalarField;
import me.xam4lor.mathematics.Point;
import me.xam4lor.utils.Constants;
import processing.core.PFont;

public class Logic {
	private ProcessingMain m;
	private Plot plot;
	private Point[] points;
	public PFont mainFont;
	
	public Logic(ProcessingMain m) {
		this.m = m;
		
		Constants.mainFont = m.createFont("Arial", 16, true);
		
		this.instanciate();
	}
	
	public void instanciate() {
		// this.plot = new FunctionPlot(m, -10, 10, -10, 10);
		this.plot = new ScalarField(m, -10, 10, -10, 10);
		
		this.points = new Point[2];
		this.points[0] = new Point(10, 10, 255, 255, 255);
		this.points[1] = new Point(0, 0, 255, 255, 255);
	}
	
	public void update() {
		// this.points[0].setPos((float) Math.sin(System.currentTimeMillis() / 100), (float) Math.cos(System.currentTimeMillis() / 100));
		
		plot.update();
	}
	
	public void draw() {
		plot.draw(true, points);
	}
	
	public void keyPressed() {}
}
