package me.xam4lor.mathematics.functions;

import me.xam4lor.graph.Vector;
import me.xam4lor.graph.plots.Plot2D;

public class LinearFunction extends Function {
	private float a;
	private float b;
	
	/**
	 * Create a linear function f(x) = a*x + b
	 * @param plot
	 * 	Plot of the function
	 * @param a
	 * @param b
	 */
	public LinearFunction(Plot2D plot, float a, float b) {
		super(plot, null, null, 0.01f, new Vector(218, 195, 22));
		
		this.a = a;
		this.b = b;
	}
	
	@Override
	public void update() {
		super.update();
	}

	@Override
	public float getYOfX(float val) {
		return a * val + b;
	}

//	@Override
//	public float getSolutionArcIntersectForR(int R) {
//		// double x = (-a*b+Math.sqrt(-(b*b) + R*R + a*a * R*R))/(1 + a*a);
//		double x = 0.5f;
//		return (float) Math.acos(x);
//	}
}
