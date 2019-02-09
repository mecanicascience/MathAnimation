package me.xam4lor.graph;

import me.xam4lor.main.ProcessingMain;
import me.xam4lor.mathematics.Point;

public class Simple2DPlot extends Plot {
	public Simple2DPlot(ProcessingMain m, int xmin, int xmax, int ymin, int ymax) {
		super(m, xmin, xmax, ymin, ymax);
	}
	
	
	@Override
	public void draw(boolean showAxes, Point[] points) {
		if(showAxes) this.showAxes(1);
		
		for (Point p : points) {
			m.noFill();
			m.stroke(p.col.x, p.col.y, p.col.z);
			m.strokeWeight(10);
			this.point(p.pos.x, p.pos.y);
		}
	}


	@Override
	public void update() {}
}
