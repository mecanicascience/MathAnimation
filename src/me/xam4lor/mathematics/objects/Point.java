package me.xam4lor.mathematics.objects;

import java.text.DecimalFormat;

import me.xam4lor.graph.Vector;
import me.xam4lor.graph.plots.Plot2D;
import me.xam4lor.main.ProcessingMain;
import me.xam4lor.utils.Constants;

public class Point {
	/** Position of the point */
	private Vector pos;
	/** Color of the point */
	private Vector col;
	
	/** true : show the point name */
	private boolean showName;
	/** true : show the point X value on the graph */
	private boolean showXGraphC;
	/** true : show the point Y value on the graph */
	private boolean showYGraphC;
	
	
	private Path path;
	private boolean animated;
	private int targetIndex;
	private double lastPosX;
	private double lastPosY;
	
	
	/**
	 * Create a Point
	 * @param x
	 * @param y
	 * @param showName
	 * 	true : display the point name
	 * @param r
	 * 	Red color value
	 * @param g
	 * 	Green color value
	 * @param b
	 * 	Blue color value
	 * @param path
	 * 	Path of the point
	 * @param showXGraphC
	 * 	true : show X position on the graph
	 * @param showYGraphC
	 * 	true : show Y position on the graph
	 */
	public Point(float x, float y, boolean showName, float r, float g, float b, Path path, boolean showXGraphC, boolean showYGraphC) {
		this.pos = new Vector(x, y);
		this.col = new Vector(r, g, b);
		
		this.showName = showName;
		this.showXGraphC = showXGraphC;
		this.showYGraphC = showYGraphC;
		
		
		if(path != null) {
			this.animated = true;
			path.initAt(x, y);
		}
		else this.animated = false;
		this.path = path;
		
		
		this.targetIndex = 1;
		this.lastPosX = x;
		this.lastPosY = y;
	}
	
	
	/**
	 * Create a Point
	 * @param x
	 * @param y
	 * @param showName
	 * 	true : display the point name
	 * @param r
	 * 	Red color value
	 * @param g
	 * 	Green color value
	 * @param b
	 * 	Blue color value
	 * @param path
	 * 	Path of the point
	 */
	public Point(float x, float y, boolean showName, float r, float g, float b, Path path) {
		this(x, y, showName, r, g, b, path, false, false);
	}
	
	/**
	 * Create a Point
	 * @param x
	 * @param y
	 * @param showName
	 * 	true : display the point name
	 * @param r
	 * 	Red color value
	 * @param g
	 * 	Green color value
	 * @param b
	 * 	Blue color value
	 */
	public Point(float x, float y, boolean showName, float r, float g, float b) {
		this(x, y, showName, r, g, b, null, false, false);
	}
	
	
	
	
	/**
	 * Update the point position value based on the point animation path
	 */
	public void update() {
		double tcurr = System.currentTimeMillis() / 1000d - Constants.initTimeSeconds;
		
		if(animated && this.path.isIndex(targetIndex)) {
			this.pos.x = (float) (this.lastPosX + ((this.path.getVectorForID(targetIndex).x - this.path.getVectorForID(targetIndex - 1).x) * (tcurr - this.path.getTimeForID(targetIndex - 1)) / (this.path.getTimeForID(targetIndex) - this.path.getTimeForID(targetIndex - 1))));
			this.pos.y = (float) (this.lastPosY + ((this.path.getVectorForID(targetIndex).y - this.path.getVectorForID(targetIndex - 1).y) * (tcurr - this.path.getTimeForID(targetIndex - 1)) / (this.path.getTimeForID(targetIndex) - this.path.getTimeForID(targetIndex - 1))));
			
			if(tcurr >= this.path.getTimeForID(targetIndex)) {
				this.lastPosX = this.pos.x;
				this.lastPosY = this.pos.y;
				this.targetIndex++;
			}
		}
	}

	

	
	/**
	 * Draw the point
	 * @param m
	 * 	ProcessingMain instance
	 * @param p
	 * 	Plot where the point will be drawn
	 */
	public void draw(ProcessingMain m, Plot2D p) {
		m.noFill();
		m.stroke(this.col.x, this.col.y, this.col.z);
		m.strokeWeight(10);
		p.point(this.pos.x, this.pos.y);
		
		if(this.showName) {
			m.textFont(Constants.mainFont, 19);
			m.fill(this.col.x, this.col.y, this.col.z, 200);
			p.text(
				"A(" + new DecimalFormat("#.##").format(this.pos.x) + " ; " + new DecimalFormat("#.##").format(this.pos.y) + ")",
				this.pos.x + 0.2f,
				this.pos.y + 0.15f
			);
		}
		
		if(this.showXGraphC) {
			m.strokeWeight(2);
			m.textFont(Constants.mainFont, 16);
			p.line(this.pos.x, 0, this.pos.x, this.pos.y);
			if(this.pos.y > 0) {
				p.text("x=" + new DecimalFormat("#.##").format(this.pos.x), this.pos.x + 0.1f, 0.2f);
			}
			else {
				p.text("x=" + new DecimalFormat("#.##").format(this.pos.x), this.pos.x - 0.3f, 0.2f);
			}
		}
		
		if(this.showYGraphC) {
			m.strokeWeight(2);
			m.textFont(Constants.mainFont, 16);
			p.line(0, this.pos.y, this.pos.x, this.pos.y);
			if(this.pos.x > 0) {
				p.text("y=" + new DecimalFormat("#.##").format(this.pos.y), -1.25f, this.pos.y - 0.1f);
			}
			else {
				p.text("y=" + new DecimalFormat("#.##").format(this.pos.y), -1.50f, this.pos.y - 0.4f);
			}
		}
	}
	
	
	
	
	/**
	 * Set the position of the point
	 * @param x
	 * @param y
	 */
	public void setPos(float x, float y) {
		this.pos.x = x;
		this.pos.y = y;
	}
	
	
	
	public float getX() { return this.pos.x; }
	public float getY() { return this.pos.y; }
	
	public float getColorR() { return this.col.x; }
	public float getColorG() { return this.col.y; }
	public float getColorB() { return this.col.z; }
}
