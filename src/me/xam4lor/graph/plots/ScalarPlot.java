package me.xam4lor.graph.plots;


import java.util.ArrayList;
import java.util.List;

import me.xam4lor.graph.Plot;
import me.xam4lor.main.ProcessingMain;
import me.xam4lor.mathematics.Point;
import me.xam4lor.mathematics.Scalar;

public class ScalarPlot extends Plot {
	private List<Scalar> scalars;
	
	public ScalarPlot(ProcessingMain m, int xmin, int xmax, int ymin, int ymax) {
		super(m, xmin, xmax, ymin, ymax);
		
		scalars = new ArrayList<Scalar>();
		
		this.instanciateScalarField(1);
	}
	
	@Override
	public void update() {
		for (Scalar s : scalars) {
			s.theta += 0.01f;
			s.r = (float) ((float) Math.sin(s.theta - s.origin.pos.x) / 4);
		}
	}
	
	@Override
	public void draw(boolean showAxes, boolean showGrid, Point[] points, Scalar[] scalars) {
		super.draw(showAxes, showGrid, points, scalars);
		
		for (Scalar s : this.scalars) {
			m.noFill();
			m.stroke(s.origin.col.x, s.origin.col.y, s.origin.col.z);
			m.strokeWeight(2);
			this.scalar(s);
		}
	}


	
	private void instanciateScalarField(int precisionLevel) {
		int xCur = this.xmin;
		while(xCur <= this.xmax) {
			int yCur = this.ymin;
			
			while(yCur <= this.ymax) {
				this.scalars.add(new Scalar(new Point(xCur, yCur), 0.1f, xCur + yCur));
				yCur += precisionLevel;
			}
			
			xCur += precisionLevel;
		}
	}
}
