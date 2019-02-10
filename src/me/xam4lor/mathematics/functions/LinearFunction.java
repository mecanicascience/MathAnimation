package me.xam4lor.mathematics.functions;

import me.xam4lor.graph.Plot;
import me.xam4lor.graph.Vector;

public class LinearFunction extends Function {
	private float a;
	private float b;
	
	public LinearFunction(Plot plot) {
		super(plot, 0.01f, new Vector(218, 195, 22));
		
		this.a = 2;
		this.b = 0;
	}
	
	@Override
	public void update() {
		super.update();
	}

	@Override
	public float getYOfX(float val) {
		return a * val + b;
	}

	@Override
	public boolean isCoupleInInterval(float x, float y) {
		return true;
	}

//	@Override
//	public float getSolutionArcIntersectForR(int R) {
//		// double x = (-a*b+Math.sqrt(-(b*b) + R*R + a*a * R*R))/(1 + a*a);
//		double x = 0.5f;
//		return (float) Math.acos(x);
//	}
}
