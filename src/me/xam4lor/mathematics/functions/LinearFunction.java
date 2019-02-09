package me.xam4lor.mathematics.functions;

import me.xam4lor.graph.Plot;
import me.xam4lor.graph.Vector;

public class LinearFunction extends Function {
	public LinearFunction(Plot plot) {
		super(plot, 0.01f, new Vector(218, 195, 22), false);
	}

	@Override
	protected float getYOfX(float val) {
		return val;
	}
}
