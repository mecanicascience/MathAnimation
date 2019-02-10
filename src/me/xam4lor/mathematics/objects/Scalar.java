package me.xam4lor.mathematics.objects;

import me.xam4lor.graph.Plot;
import me.xam4lor.main.ProcessingMain;

public class Scalar {
	/** Origin of the scalar */
	private Point origin;
	/** Module of the scalar */
	private float r;
	/** Argument of the scalar */
	private float theta;
	
	/**
	 * Create a new vector
	 * @param origin
	 * 	Origin Point of the vector
	 * @param r
	 * 	Radius of the vector
	 * @param theta
	 * 	Theta angle (in radians)
	 */
	public Scalar(Point origin, float r, float theta) {
		this.origin = origin;
		this.r = r;
		this.theta = theta;
	}

	
	
	/**
	 * Update of the scalar
	 */
	public void update() {}

	/**
	 * Draw the scalar
	 * @param m
	 * 	ProcessingMain class
	 * @param p
	 * 	Plot to be drawn on
	 */
	public void draw(ProcessingMain m, Plot p) {
		m.stroke(this.origin.getColorR(), this.origin.getColorG(), this.origin.getColorB());
		m.fill(this.origin.getColorR(), this.origin.getColorG(), this.origin.getColorB());
		m.strokeWeight(2);
		
		p.scalar(this);
	}
	
	
	
	
	public float getX() { return this.origin.getX(); }
	public float getY() { return this.origin.getY(); }
	
	public float getR() { return this.r; }
	public float getTheta() { return this.theta; }

	public float getColorR() { return this.origin.getColorR(); }
	public float getColorG() { return this.origin.getColorG(); }
	public float getColorB() { return this.origin.getColorB(); }
	
	
	public void setR(float r) { this.r = r; }
	public void setTheta(float theta) { this.theta = theta; }
}
