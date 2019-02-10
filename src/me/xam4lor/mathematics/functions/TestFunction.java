package me.xam4lor.mathematics.functions;

import me.xam4lor.graph.Vector;
import me.xam4lor.graph.plots.Plot2D;

public class TestFunction extends Function {
	public float theta;
	
	public TestFunction(Plot2D plot) {
		super(plot, new Vector(0, 200), new Vector(0, 200), 0.01f, new Vector(37, 168, 238));
		
		theta = 0;
	}
	
	@Override
	public void update() {
		super.update();
		
		theta += 0.001f;
	}

	@Override
	public float getYOfX(float x) {
		return (float) (-9.81 / (2 * Math.pow(8, 2) * Math.pow(Math.cos(theta), 2)) * Math.pow(x, 2) + Math.tan(3.14 / 4) * x);
	}

//	@Override
//	public float getSolutionArcIntersectForR(int R) {
//		return 0;
//	}
}
