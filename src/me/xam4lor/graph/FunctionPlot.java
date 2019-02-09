package me.xam4lor.graph;

import java.util.ArrayList;
import java.util.List;

import me.xam4lor.main.ProcessingMain;
import me.xam4lor.mathematics.Function;
import me.xam4lor.mathematics.LinearFunction;
import me.xam4lor.mathematics.Point;
import me.xam4lor.mathematics.TestFunction;

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
	public void draw(boolean showAxes, Point[] points) {
		if(showAxes) this.showAxes(1);
		
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

