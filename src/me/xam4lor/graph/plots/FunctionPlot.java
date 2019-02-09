package me.xam4lor.graph.plots;

import java.util.ArrayList;
import java.util.List;

import me.xam4lor.graph.Plot;
import me.xam4lor.main.ProcessingMain;
import me.xam4lor.mathematics.Point;
import me.xam4lor.mathematics.Scalar;
import me.xam4lor.mathematics.functions.Function;
import me.xam4lor.mathematics.functions.LinearFunction;
import me.xam4lor.mathematics.functions.TestFunction;

public class FunctionPlot extends Plot {
	private List<Function> functions;
	
	public FunctionPlot(ProcessingMain m, int xmin, int xmax, int ymin, int ymax) {
		super(m, xmin, xmax, ymin, ymax);
		
		this.functions = new ArrayList<Function>();
		this.functions.add(new LinearFunction(this));
		this.functions.add(new TestFunction(this));
	}

	@Override
	public void update() {
		for (Function function : functions) {
			function.update();
		}
	}
	
	@Override
	public void draw(boolean showAxes, boolean showGrid, Point[] points, Scalar[] scalars) {
		super.draw(showAxes, showGrid, points, scalars);
		
		for (Function function : functions) {
			Point[] a = function.points.toArray(new Point[function.points.size()]);
			for (int i = 0; i < a.length - 1; i++) {
				Point p = a[i];
				
				m.noFill();
				m.stroke(function.color.x, function.color.y, function.color.z);
				m.strokeWeight(2);
				this.line(p.pos.x, p.pos.y, a[i + 1].pos.x, a[i + 1].pos.y);
			}
		}
	}
}

