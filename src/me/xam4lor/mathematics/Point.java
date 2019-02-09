package me.xam4lor.mathematics;

import me.xam4lor.graph.Vector;

public class Point {
	public Vector pos;
	public Vector col;
	
	public Point(float x, float y, float r, float g, float b) {
		this.pos = new Vector(x, y);
		this.col = new Vector(r, g, b);
	}
	
	public Point(float x, float y) {
		this(x, y, 255, 255, 255);
	}

	public void setPos(float x, float y) {
		this.pos.x = x;
		this.pos.y = y;
	}
}
