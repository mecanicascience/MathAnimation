package me.xam4lor.graph.plots;


import java.util.ArrayList;
import java.util.List;

import me.xam4lor.graph.Plot;
import me.xam4lor.main.ProcessingMain;
import me.xam4lor.mathematics.objects.Point;
import me.xam4lor.mathematics.objects.Scalar;

public class PlotScalar extends Plot {
	/** List of all the scalar */
	private List<Scalar> scal;
	
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
	public PlotScalar(ProcessingMain m, int xmin, int xmax, int ymin, int ymax) {
		super(m, xmin, xmax, ymin, ymax);
		
		scal = new ArrayList<Scalar>();
		
		this.instanciateScalarField(1);
	}
	
	
	
	@Override
	public void update(Point[] points, Scalar[] scalars) {
		super.update(points, scalars);
		
		for (Scalar s : this.scal) {
			s.setTheta(0.01f + s.getTheta());
			s.setR((float) ((float) Math.sin(s.getTheta() - s.getX()) / 4));
		}
	}
	
	@Override
	public void draw(boolean showAxes, boolean showGrid, Point[] points, Scalar[] scalars) {
		super.draw(showAxes, showGrid, points, scalars);
		
		for (Scalar s : this.scal) {
			m.noFill();
			m.stroke(s.getColorR(), s.getColorG(), s.getColorB());
			m.strokeWeight(2);
			this.scalar(s);
		}
	}


	/**
	 * Instanciate the scalar Field
	 * @param precisionLevel
	 * 	Level of details of scalar number
	 */
	private void instanciateScalarField(int precisionLevel) {
		int xCur = this.xmin;
		while(xCur <= this.xmax) {
			int yCur = this.ymin;
			
			while(yCur <= this.ymax) {
				this.scal.add(new Scalar(new Point(xCur, yCur, false, 255, 255, 255), 0.1f, xCur + yCur));
				yCur += precisionLevel;
			}
			
			xCur += precisionLevel;
		}
	}
}
