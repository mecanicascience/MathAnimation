package me.xam4lor.graph.plots;

import java.util.ArrayList;
import java.util.List;

import me.xam4lor.graph.Plot;
import me.xam4lor.main.ProcessingMain;
import me.xam4lor.mathematics.functions.Function;
import me.xam4lor.mathematics.functions.LinearFunction;
import me.xam4lor.mathematics.functions.TestFunction;
import me.xam4lor.mathematics.objects.Point;
import me.xam4lor.mathematics.objects.Scalar;

public class FunctionPlot extends Plot {
	/** List of all the plot functions */
	private List<Function> functions;
	
	/**
	 * Create a plot that draw functions
	 * @param m
	 * 	ProcessingMain instance
	 * @param xmin
	 * 	Min X value rendered
	 * @param xmax
	 * 	Max X value rendered
	 * @param ymin
	 * 	Min Y value rendered
	 * @param ymax
	 * 	Max Y value rendered
	 */
	public FunctionPlot(ProcessingMain m, int xmin, int xmax, int ymin, int ymax) {
		super(m, xmin, xmax, ymin, ymax);
		
		this.functions = new ArrayList<Function>();
		this.functions.add(new LinearFunction(this));
		this.functions.add(new TestFunction(this));
	}

	
	
	@Override
	public void update(Point[] points, Scalar[] scalars) {
		super.update(points, scalars);
		
		for (Function function : functions) {
			function.update();
		}
	}
	
	
	@Override
	public void draw(boolean showAxes, boolean showGrid, Point[] points, Scalar[] scalars) {
		super.draw(showAxes, showGrid, points, scalars);
		
		for (Function function : functions) {
			Point[] a = function.getPoints().toArray(new Point[function.getPoints().size()]);
			for (int i = 0; i < a.length - 1; i++) {
				Point p = a[i];

				if(!function.isCoupleInInterval(p.getX(), p.getY())) continue;
				
				m.noFill();
				m.stroke(function.getR(), function.getG(), function.getB());
				m.strokeWeight(2);
				this.line(p.getX(), p.getY(), a[i + 1].getX(), a[i + 1].getY());
			}
		}
		
//		this.arc(0, 0, 1, 1, 0, this.functions.get(0).getSolutionArcIntersectForR(1));
	}
}

