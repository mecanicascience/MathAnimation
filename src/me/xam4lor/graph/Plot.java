package me.xam4lor.graph;

import java.text.DecimalFormat;

import me.xam4lor.main.ProcessingMain;
import me.xam4lor.mathematics.Point;
import me.xam4lor.mathematics.Scalar;
import me.xam4lor.utils.Constants;

public abstract class Plot {
	protected ProcessingMain m;
	protected int xmin, xmax, ymin, ymax;
	protected int xUnit, yUnit, offsetX, offsetY;
	
	/**
	 * Plot instanciation
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
	public Plot(ProcessingMain m, int xmin, int xmax, int ymin, int ymax) {
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
	}
	
	
	
	/**
	 * Draw a point
	 * @param x
	 * @param y
	 */
	protected void point(float x, float y) {
		m.pushMatrix();
		
		m.translate(Constants.WIDTH / 2, Constants.HEIGHT / 2);
		m.point(x * 2 * this.xUnit + offsetX, -2 * y * this.yUnit - offsetY);
		
		m.popMatrix();
	}
	
	protected void text(String text, float x, float y) {
		m.pushMatrix();
		
		m.translate(Constants.WIDTH / 2, Constants.HEIGHT / 2);
		m.text(text, x * 2 * this.xUnit + offsetX, -2 * y * this.yUnit - offsetY);
		
		m.popMatrix();
	}
	
	/**
	 * Draw a line
	 * @param x
	 * @param y
	 * @param x2
	 * @param y2
	 */
	protected void line(float x, float y, float x2, float y2) {
		m.pushMatrix();
		
		m.translate(Constants.WIDTH / 2, Constants.HEIGHT / 2);
		m.line(
			x * 2 * this.xUnit + offsetX,
			-2 * y * this.yUnit - offsetY,
			x2 * 2 * this.xUnit + offsetX,
			-2 * y2 * this.yUnit - offsetY
		);
		
		m.popMatrix();
	}
	
	
	
	
	/**
	 * Draw a scalar vector
	 * @param vec
	 * 	Scalar vector
	 */
	protected void scalar(Scalar vec) {
		m.pushMatrix();
		
		m.strokeWeight(Math.abs(vec.r) * 2);
		m.translate(Constants.WIDTH / 2, Constants.HEIGHT / 2);
		m.line(
			(float) vec.origin.pos.x * 2 * this.xUnit + offsetX,
			(float) -vec.origin.pos.y * 2 * this.yUnit + offsetY,
			(float) (vec.origin.pos.x + vec.r * Math.cos(vec.theta)) * 2 * this.xUnit + offsetX,
			(float) -(vec.origin.pos.y + vec.r * Math.sin(vec.theta)) * 2 * this.yUnit + offsetY
		);
		
		m.translate(
			(float) (vec.origin.pos.x + vec.r * Math.cos(vec.theta)) * 2 * this.xUnit + offsetX, 
			(float) -(vec.origin.pos.y + vec.r * Math.sin(vec.theta)) * 2 * this.yUnit + offsetY
		);
		m.rotate(vec.theta - 1.58f);
		m.triangle(-vec.r * Constants.VEC_SIZE_ARROW, 0, 0, -vec.r * Constants.VEC_SIZE_ARROW * 2, vec.r * Constants.VEC_SIZE_ARROW, 0);
		
		m.popMatrix();
	}
	
	
	
	
	/**
	 * Draw axes
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
	

	public void draw(boolean showAxes, boolean showGrid, Point[] points, Scalar[] scalars) {
		if(showAxes) this.showAxes(1, showGrid);
		
		for (Point p : points) {
			m.noFill();
			m.stroke(p.col.x, p.col.y, p.col.z);
			m.strokeWeight(10);
			this.point(p.pos.x, p.pos.y);
			
			if(p.showName) {
				m.textFont(Constants.mainFont, 19);
				m.fill(p.col.x, p.col.y, p.col.z, 200);
				this.text(
					"A(" + new DecimalFormat("#.##").format(p.pos.x) + " ; " + new DecimalFormat("#.##").format(p.pos.y) + ")",
					p.pos.x + 0.2f,
					p.pos.y + 0.15f
				);
			}
			
			if(p.showXGraphC) {
				m.strokeWeight(2);
				m.textFont(Constants.mainFont, 16);
				this.line(p.pos.x, 0, p.pos.x, p.pos.y);
				if(p.pos.y > 0) {
					this.text("x=" + new DecimalFormat("#.##").format(p.pos.x), p.pos.x + 0.1f, 0.2f);
				}
				else {
					this.text("x=" + new DecimalFormat("#.##").format(p.pos.x), p.pos.x - 0.3f, 0.2f);
				}
			}
			
			if(p.showYGraphC) {
				m.strokeWeight(2);
				m.textFont(Constants.mainFont, 16);
				this.line(0, p.pos.y, p.pos.x, p.pos.y);
				if(p.pos.x > 0) {
					this.text("y=" + new DecimalFormat("#.##").format(p.pos.y), -1.05f, p.pos.y - 0.1f);
				}
				else {
					this.text("y=" + new DecimalFormat("#.##").format(p.pos.y), -1.05f, p.pos.y - 0.4f);
				}
			}
		}
		
		
		for (Scalar s : scalars) {
			m.stroke(s.origin.col.x, s.origin.col.y, s.origin.col.z);
			m.fill(s.origin.col.x, s.origin.col.y, s.origin.col.z);
			m.strokeWeight(2);
			
			this.scalar(s);
		}
	}
	
	public abstract void update();
}
