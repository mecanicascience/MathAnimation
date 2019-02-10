package me.xam4lor.main;

import java.util.ArrayList;
import java.util.List;

import me.xam4lor.graph.Plot;
import me.xam4lor.graph.plots.FunctionPlot;
import me.xam4lor.mathematics.objects.Point;
import me.xam4lor.mathematics.objects.Scalar;
import me.xam4lor.utils.Constants;

public class Logic {
	/** Main Processing instance */
	private ProcessingMain m;
	/** Main Plot displayed */
	private Plot plot;
	
	/** List of all points */
	private List<Point> points;
	/** List of all scalars */
	private List<Scalar> scalars;
	
	
	/**
	 * Main Logic
	 * @param m
	 * 	ProcessingMain instance
	 */
	public Logic(ProcessingMain m) {
		this.m = m;
		
		Constants.mainFont = m.createFont("Arial", 16, true);
		this.points = new ArrayList<Point>();
		this.scalars = new ArrayList<Scalar>();
		
		this.instanciate();
	}
	
	
	/**
	 * Instanciate the main logic
	 */
	private void instanciate() {
		this.plot = new FunctionPlot(m, -10, 20, -10, 20);
		// this.plot = new ScalarPlot(m, -10, 10, -10, 10);
		
		
		this.points.add(new Point(1, 1, true, 253, 57, 237, true, true));
		// this.scalars.add(new Scalar(this.points.get(0), 2, PConstants.PI / 4));
	}
	
	
	
	/**
	 * On update
	 */
	public void update() {
		plot.update(points.toArray(new Point[points.size()]), scalars.toArray(new Scalar[scalars.size()]));
	}
	
	
	/**
	 * On draw
	 */
	public void draw() {
		plot.draw(true, true, points.toArray(new Point[points.size()]), scalars.toArray(new Scalar[scalars.size()]));
	}
	
	
	/**
	 * On key pressed
	 */
	public void keyPressed() {}
}
