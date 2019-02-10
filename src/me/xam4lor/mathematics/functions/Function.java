package me.xam4lor.mathematics.functions;

import java.util.ArrayList;
import java.util.List;

import me.xam4lor.graph.Plot;
import me.xam4lor.graph.Vector;
import me.xam4lor.mathematics.objects.Point;

public abstract class Function {
	/** Plot on which the function is displayed */
	private Plot plot;
	/** Incrementation value of X when drawing the function */
	private float precisionLevel;
	/** Color of the function */
	private Vector color;
	/** List of all the displayed points to draw the function */
	private List<Point> points;
	
	
	/**
	 * Create a function
	 * @param plot
	 * 	Plot on which the function is displayed
	 * @param precisionLevel
	 * 	Incrementation value of X when drawing the function
	 * @param color
	 * 	Color of the function
	 */
	public Function(Plot plot, float precisionLevel, Vector color) {
		this.plot = plot;
		this.precisionLevel = precisionLevel;
		this.color = color;
		
		this.instanciate();
	}
	
	
	
	/**
	 * Update of the function
	 */
	public void update() {
		for (Point point : points) {
			float x = point.getX();
			point.setPos(x, this.getYOfX(x));
		}
	}
	
	
	
	/**
	 * Instanciate the function
	 */
	private void instanciate() {
		int xMin = this.plot.getXMin();
		int xMax = this.plot.getXMax();
		this.points = new ArrayList<Point>();
		
		float xCur = xMin - 1;
		while(xCur < xMax + 1) {
			this.points.add(new Point(xCur, this.getYOfX(xCur)));
			xCur += this.precisionLevel;
		}
	}
	
	
	/**
	 * TODO : post the definition interval on the function params
	 * Check if a couple of points is on the interval of definition of the function
	 * @param x
	 * @param y
	 * @return true if they are
	 */
	public abstract boolean isCoupleInInterval(float x, float y);
	
	/**
	 * @param x
	 * @return f(x)
	 */
	public abstract float getYOfX(float x);
	
//	public abstract float getSolutionArcIntersectForR(int R);
	
	
	
	public List<Point> getPoints() { return this.points; }
	
	public float getR() { return this.color.x; }
	public float getG() { return this.color.y; }
	public float getB() { return this.color.z; }
}
