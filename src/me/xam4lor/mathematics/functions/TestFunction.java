package me.xam4lor.mathematics.functions;

import me.xam4lor.graph.Plot;
import me.xam4lor.graph.Vector;

public class TestFunction extends Function {
	private float theta;
	
	public TestFunction(Plot plot) {
		super(plot, 0.01f, new Vector(37, 168, 238), true);
	}

	@Override
	protected float getYOfX(float val) {
		return (float) (Math.sin(val + theta) * Math.tan(val));
	}

	@Override
	public void update() {
		theta += 0.01f;
		
		super.update();
	}

}
