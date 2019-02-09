package me.xam4lor.main;

import java.util.ArrayList;
import java.util.List;

import me.xam4lor.graph.Plot;
import me.xam4lor.graph.plots.ScalarPlot;
import me.xam4lor.mathematics.Point;
import me.xam4lor.mathematics.Scalar;
import me.xam4lor.utils.Constants;
import processing.core.PConstants;
import processing.core.PFont;

public class Logic {
	private ProcessingMain m;
	private Plot plot;
	public PFont mainFont;
	
	private List<Point> points;
	private List<Scalar> scalars;
	
	float theta;
	
	public Logic(ProcessingMain m) {
		this.m = m;
		
		Constants.mainFont = m.createFont("Arial", 16, true);
		this.points = new ArrayList<Point>();
		this.scalars = new ArrayList<Scalar>();
		
		this.instanciate();
	}
	
	public void instanciate() {
		theta = 0;
		
		// this.plot = new FunctionPlot(m, -10, 10, -10, 10);
		this.plot = new ScalarPlot(m, -10, 10, -10, 10);
		
		
		this.points.add(new Point(3.4123f, 3, true, 253, 57, 237, true, true));
		
		this.scalars.add(new Scalar(this.points.get(0), 2, PConstants.PI / 4));
	}
	
	public void update() {
		this.points.get(0).setPos((float) Math.sin(theta) * 3, (float) Math.cos(theta) * 6);
		
		plot.update();
		theta += 0.01f;
	}
	
	public void draw() {
		plot.draw(true, true, points.toArray(new Point[points.size()]), scalars.toArray(new Scalar[scalars.size()]));
	}
	
	public void keyPressed() {}
}
