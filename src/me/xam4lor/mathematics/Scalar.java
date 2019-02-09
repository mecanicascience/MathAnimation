package me.xam4lor.mathematics;

public class Scalar {
	public Point origin;
	public float r;
	public float theta;
	
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
}
