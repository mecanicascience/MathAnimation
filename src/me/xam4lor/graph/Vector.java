package me.xam4lor.graph;

public class Vector {
	public float x;
	public float y;
	public float z;
	
	/**
	 * Create a 2D vector (z = undefined)
	 * @param x
	 * @param y
	 */
	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Create a 3D vector
	 * @param x
	 * @param y
	 * @param z
	 */
	public Vector(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
