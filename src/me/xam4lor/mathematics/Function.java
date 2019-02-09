package me.xam4lor.mathematics;

import java.util.ArrayList;
import java.util.List;

import me.xam4lor.graph.Plot;
import me.xam4lor.graph.Vector;

public abstract class Function {
	private Plot plot;
	private float precisionLevel;
	private boolean animated;
	
	public Vector color;
	public List<Point> points;
	
	public Function(Plot plot, float precisionLevel, Vector color, boolean animated) {
		this.plot = plot;
		this.precisionLevel = precisionLevel;
		this.color = color;
		this.animated = animated;
		
		this.instanciate();
	}
	
	public void instanciate() {
		int xMin = this.plot.getXMin();
		int xMax = this.plot.getXMax();
		this.points = new ArrayList<Point>();
		
		float xCur = xMin - 1;
		while(xCur < xMax + 1) {
			this.points.add(new Point(xCur, this.getYOfX(xCur)));
			xCur += this.precisionLevel;
		}
	}
	
	
	public void update() {
		if(!animated) return;
		
		for (Point point : points) {
			point.pos.y = this.getYOfX(point.pos.x);
		}
	}
	
	protected abstract float getYOfX(float x);
}
