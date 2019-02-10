package me.xam4lor.mathematics.functions;

import java.util.ArrayList;
import java.util.List;

import me.xam4lor.graph.Vector;
import me.xam4lor.graph.plots.Plot2D;
import me.xam4lor.main.ProcessingMain;
import me.xam4lor.mathematics.objects.Point;

public abstract class Function {
	/** Plot on which the function is displayed */
	private Plot2D plot;
	/** Incrementation value of X when drawing the function */
	private float precisionLevel;
	
	/** x interval of definition (null for none) */
	private Vector defIntervalX;
	/** y interval of definition (null for none) */
	private Vector defIntervalY;
	
	/** Color of the function */
	private Vector color;
	/** List of all the displayed points to draw the function */
	private List<Point> points;
	
	
	/**
	 * Create a function
	 * @param plot
	 * 	Plot on which the function is displayed
	 * @param defIntervalX
	 * 	x interval of definition (null for none)
	 * @param defIntervalY 
	 * 	y interval of definition (null for none)
	 * @param precisionLevel
	 * 	Incrementation value of X when drawing the function
	 * @param color
	 * 	Color of the function
	 */
	public Function(Plot2D plot, Vector defIntervalX, Vector defIntervalY, float precisionLevel, Vector color) {
		this.plot = plot;
		this.precisionLevel = precisionLevel;
		this.color = color;
		this.defIntervalX = defIntervalX;
		this.defIntervalY = defIntervalX;
		
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
	 * Draw the function
	 * @param m
	 * 	ProcessingMain instance
	 * @param plot
	 * 	Plot2D main
	 */
	public void draw(ProcessingMain m, Plot2D plot) {
		Point[] a = this.getPoints().toArray(new Point[this.getPoints().size()]);
		for (int i = 0; i < a.length - 1; i++) {
			Point p = a[i];

			if(!this.isCoupleInInterval(p.getX(), p.getY())) continue;
			
			m.noFill();
			m.stroke(this.getR(), this.getG(), this.getB());
			m.strokeWeight(2);
			plot.line(p.getX(), p.getY(), a[i + 1].getX(), a[i + 1].getY());
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
			this.points.add(new Point(xCur, this.getYOfX(xCur), false, 255, 255, 255));
			xCur += this.precisionLevel;
		}
	}
	
	
	/**
	 * Check if a couple of points is on the interval of definition of the function
	 * @param x
	 * @param y
	 * @return true if they are
	 */
	public boolean isCoupleInInterval(float x, float y) {
		boolean belong = true;
		
		if(this.defIntervalX != null) {
			if(x > defIntervalX.x && x < defIntervalX.y) belong = true;
			else return false;
		}
		else belong = true;
		
		if(this.defIntervalY != null) {
			if(y > defIntervalY.x && y < defIntervalY.y) belong = true;
			else return false;
		}
		else belong = true;
		
		return belong;
	}
	
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
