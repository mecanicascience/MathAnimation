package me.xam4lor.mathematics;

import me.xam4lor.graph.Vector;

public class Point {
	public Vector pos;
	public Vector col;
	public boolean showName;
	public boolean showXGraphC;
	public boolean showYGraphC;
	
	public Point(float x, float y, boolean showName, float r, float g, float b, boolean showXGraphC, boolean showYGraphC) {
		this.pos = new Vector(x, y);
		this.col = new Vector(r, g, b);
		
		this.showName = showName;
		this.showXGraphC = showXGraphC;
		this.showYGraphC = showYGraphC;
	}
	
	public Point(float x, float y, boolean showName, float r, float g, float b) {
		this(x, y, showName, r, g, b, false, false);
	}
	
	public Point(float x, float y, boolean showName) {
		this(x, y, showName, 255, 255, 255);
	}
	
	public Point(float x, float y) {
		this(x, y, false, 255, 255, 255);
	}
	
	

	public void setPos(float x, float y) {
		this.pos.x = x;
		this.pos.y = y;
	}
}
