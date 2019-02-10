package me.xam4lor.graph.plots;

import java.util.ArrayList;
import java.util.List;

import me.xam4lor.main.ProcessingMain;
import me.xam4lor.mathematics.functions.Function;
import me.xam4lor.mathematics.objects.Point;
import me.xam4lor.mathematics.objects.Scalar;
import me.xam4lor.utils.Constants;
import processing.core.PConstants;

public class Plot2D {
	/** Main instance */
	protected ProcessingMain m;
	/** x and y plot values */
	protected int xmin, xmax, ymin, ymax;
	/** x and y plot units */
	protected int xUnit, yUnit, offsetX, offsetY;
	/** List of all the plot functions */
	protected List<Function> functions;
	
	
	
	/**
	 * Plot instance
	 * @param m
	 * 	Main Instance
	 * @param xmin
	 * 	Minimum x value
	 * @param xmax
	 * 	Maximum x value
	 * @param ymin
	 * 	Minimum y value
	 * @param ymax
	 * 	Maximum y value
	 */
	public Plot2D(ProcessingMain m, int xmin, int xmax, int ymin, int ymax) {
		this.m = m;
		this.xmin = xmin;
		this.xmax = xmax;
		this.ymin = ymin;
		this.ymax = ymax;
		
		if(xmin > xmax) throw new Error("xmin must be greater than xmax");
		if(ymin > ymax) throw new Error("ymin must be greater than to ymax");
		
		this.xUnit = Constants.WIDTH  / (Math.abs(xmin) + Math.abs(xmax)) / 2;
		this.yUnit = Constants.HEIGHT / (Math.abs(ymin) + Math.abs(ymax)) / 2;
		
		this.offsetX = -(Math.abs(xmax) - Math.abs(xmin)) * xUnit;
		this.offsetY =  (Math.abs(ymax) - Math.abs(ymin)) * yUnit;
		
		this.functions = new ArrayList<Function>();
	}
	
	
	
	
	/**
	 * Update the plot
	 * @param points
	 * 	List of all points
	 * @param scalars
	 * 	List of all scalars
	 */
	public void update(Point[] points, Scalar[] scalars) {
		for (Function f : functions) f.update();
		for (Scalar s : scalars) s.update();
		for (Point p : points) p.update();
	}

	/**
	 * Draw the plot to the screen
	 * @param showAxes
	 * 	true : show Axes
	 * @param showGrid
	 * 	true : show Grid
	 * @param points
	 * 	List of all points
	 * @param scalars
	 * 	List of all scalars
	 */
	public void draw(boolean showAxes, boolean showGrid, Point[] points, Scalar[] scalars) {
		if(showAxes) this.showAxes(1, showGrid);
		
		for (Function f : functions) f.draw(m, this);
		for (Scalar s : scalars) s.draw(m, this);
		for (Point p : points) p.draw(m, this);
	}
	
	
	
	/**
	 * Set Plot functions
	 * @param functions
	 * 	List of all functions
	 */
	public void setFunctions(Function... functions) {
		for (Function f : functions) this.functions.add(f);
	}
	
	
	
	
	
	
	/**
	 * Draw a point
	 * @param x
	 * @param y
	 */
	public void point(float x, float y) {
		m.pushMatrix();
		
		m.translate(Constants.WIDTH / 2, Constants.HEIGHT / 2);
		m.point(
			x * 2 * this.xUnit + offsetX,
			-2 * y * this.yUnit + offsetY
		);
		
		m.popMatrix();
	}
	
	/**
	 * Draw a scalar vector
	 * @param vec
	 * 	Scalar vector
	 */
	public void scalar(Scalar vec) {
		m.pushMatrix();
		
		m.strokeWeight(Math.abs(vec.getR()) * 2);
		m.translate(Constants.WIDTH / 2, Constants.HEIGHT / 2);
		m.line(
			(float) vec.getX() * 2 * this.xUnit + offsetX,
			(float) -vec.getY() * 2 * this.yUnit + offsetY,
			(float) (vec.getX() + vec.getR() * Math.cos(vec.getTheta())) * 2 * this.xUnit + offsetX,
			(float) -(vec.getY() + vec.getR() * Math.sin(vec.getTheta())) * 2 * this.yUnit + offsetY
		);
		
		m.translate(
			(float) (vec.getX() + vec.getR() * Math.cos(vec.getTheta())) * 2 * this.xUnit + offsetX, 
			(float) -(vec.getY() + vec.getR() * Math.sin(vec.getTheta())) * 2 * this.yUnit + offsetY
		);
		m.rotate(vec.getTheta() - 1.58f);
		m.triangle(-vec.getR() * Constants.VEC_SIZE_ARROW, 0, 0, -vec.getR() * Constants.VEC_SIZE_ARROW * 2, vec.getR() * Constants.VEC_SIZE_ARROW, 0);
		
		m.popMatrix();
	}
	
	
	/**
	 * Draw a line
	 * @param x
	 * @param y
	 * @param x2
	 * @param y2
	 */
	public void line(float x, float y, float x2, float y2) {
		m.pushMatrix();
		
		m.translate(Constants.WIDTH / 2, Constants.HEIGHT / 2);
		m.line(
			x * 2 * this.xUnit + offsetX,
			-2 * y * this.yUnit + offsetY,
			x2 * 2 * this.xUnit + offsetX,
			-2 * y2 * this.yUnit + offsetY
		);
		
		m.popMatrix();
	}
	
	/**
	 * Draw an arc
	 * @param x
	 * 	X coordinate of the circle center
	 * @param y
	 * 	Y coordinate of the circle center
	 * @param width
	 * 	Width of the circle
	 * @param height
	 * 	Height of the circle
	 * @param startAngle
	 * 	Start angle of the arc in radians
	 * @param stopAngle
	 * 	Stop angle of the arc in radians
	 */
	public void arc(float x, float y, float width, float height, float startAngle, float stopAngle) {
		m.pushMatrix();
		
		m.translate(Constants.WIDTH / 2, Constants.HEIGHT / 2);
		
		m.arc(
			x * 2 * this.xUnit + offsetX,
			y * -2 * this.yUnit + offsetY,
			width * 4 * this.xUnit,
			height * 4 * this.yUnit,
			PConstants.TWO_PI - stopAngle - startAngle,
			PConstants.TWO_PI
		);
		
		m.popMatrix();
	}
	
	
	/**
	 * Draw a text on the screen
	 * @param text
	 * 	Text to be displayed
	 * @param x
	 * @param y
	 */
	public void text(String text, float x, float y) {
		m.pushMatrix();
		
		m.translate(Constants.WIDTH / 2, Constants.HEIGHT / 2);
		m.text(
			text,
			x * 2 * this.xUnit + offsetX,
			-2 * y * this.yUnit + offsetY
		);
		
		m.popMatrix();
	}
	
	
	
	
	
	
	/**
	 * Render axes
	 * @param graduationLevel
	 * 	Tick level (float)
	 * @param showGrid
	 * 	true : display Grid
	 */
	protected void showAxes(float graduationLevel, boolean showGrid) {
		m.pushMatrix();
		
		m.translate(Constants.WIDTH / 2, Constants.HEIGHT / 2);
		float curX, curY;
		
		
		// big graduations
		if(showGrid) {
			m.noFill();
			m.stroke(18, 110, 195, 110);
			m.strokeWeight(1f);
			
			curX = this.xmin;
			while(curX <= this.xmax) {
				if(curX != 0)
					m.line(
						curX * 2 * this.xUnit + offsetX,
						-Constants.HEIGHT,
						curX * 2 * this.xUnit + offsetX,
						Constants.HEIGHT
					);
				curX += graduationLevel;
			}
			
			curY = this.ymin;
			while(curY <= this.ymax) {
				if(curY != 0)
					m.line(
						-Constants.WIDTH,
						-curY * 2 * this.yUnit + offsetY,
						Constants.WIDTH,
						-curY * 2 * this.yUnit + offsetY
					);
				curY += graduationLevel;
			}
		}
		
		
		
		// central axe
		m.noFill();
		m.stroke(255, 255, 255, 220);
		m.strokeWeight(1);
		
		m.line(offsetX, -Constants.HEIGHT, offsetX, Constants.HEIGHT);
		m.line(-Constants.WIDTH, offsetY, Constants.WIDTH, offsetY);
		
		
		
		// ticks of central axe
		m.stroke(255, 255, 255, 150);
		m.textFont(Constants.mainFont, 16);
		m.fill(255, 255, 255, 120);
		
		curX = this.xmin;
		while(curX <= this.xmax) {
			if(curX != 0) {
				m.line(
					curX * 2 * this.xUnit + offsetX,
					-this.xUnit / 5 + offsetY,
					curX * 2 * this.xUnit + offsetX,
					this.yUnit / 5 + offsetY
				);
				m.text(
					(int) curX,
					curX * 2 * this.xUnit - this.xUnit * 0.18f + offsetX,
					this.yUnit / 5 + this.yUnit * 0.85f + offsetY
				);
			}
			curX += graduationLevel;
		}
		
		curY = this.ymin;
		while(curY <= this.ymax) {
			if(curY != 0) {
				m.line(
					-this.yUnit * 0.25f + offsetX,
					-curY * 2 * this.yUnit + offsetY,
					this.yUnit * 0.25f + offsetX,
					-curY * 2 * this.yUnit + offsetY
				);
				m.text(
					(int) curY,
					0.25f * this.xUnit + offsetX,
					-curY * 2 * this.yUnit + 0.3f * this.yUnit + offsetY
				);
			}
			
			curY += graduationLevel;
		}
		
		
		m.popMatrix();
	}

	
	
	
	public int getXMax() { return this.xmax; }
	public int getXMin() { return this.xmin; }
	public int getYMax() { return this.ymax; }
	public int getYMin() { return this.ymin; }
}
