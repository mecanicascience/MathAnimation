package me.xam4lor.mathematics.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.xam4lor.graph.Vector;

public class Path {
	/** List of all keyframes by (time, position) */
	private HashMap<Float, Vector> path;
	/** List of all keyFrames time values */
	private List<Float> times;
	
	
	/**
	 * Create a Path (by default, it begins at x=0 and y=0)
	 */
	public Path() {
		this.path = new HashMap<Float, Vector>();
		this.times = new ArrayList<Float>();
		
		this.addKeyFrame(0f, 0, 0);
	}
	
	
	/**
	 * Init the path (values must be the init position of the point)
	 * @param x
	 * 	x init
	 * @param y
	 * 	y init
	 */
	public void initAt(float x, float y) {
		this.getVectorForID(0).x = x;
		this.getVectorForID(0).y = y;
	}
	
	
	
	/**
	 * Add a key frame
	 * @param time
	 * 	Time of the key frame (seconds)
	 * @param x
	 * 	X position of the key frame
	 * @param y
	 * 	Y position of the key frame
	 * @return the path
	 */
	public Path addKeyFrame(float time, float x, float y) {
		this.path.put(time, new Vector(x, y));
		this.times.add(time);
		return this;
	}
	
	
	/**
	 * @param timeID
	 * @return the time based on the current ID
	 */
	public float getTimeForID(int timeID) {
		return this.times.get(timeID);
	}
	
	/**
	 * @param timeID
	 * @return the vector position based on the current ID
	 */
	public Vector getVectorForID(int timeID) {
		return this.path.get(this.getTimeForID(timeID));
	}

	/**
	 * @param targetIndex
	 * @return true if the targetIndex belongs to the array
	 */
	public boolean isIndex(int targetIndex) {
		if(targetIndex > this.times.size() - 1) return false;
		return true;
	}
	
	
	
	
	
	public static Path createPath() {
		return new Path();
	}
	
	public static Path createPath(float... vals) {
		Path path = Path.createPath();
		
		if(vals.length % 3 != 0) throw new RuntimeException("Path creation must have for each keyframe a time, a x and a y position.");
		
		for (int i = 0; i < vals.length - 1; i++) if(i % 3 == 0) path.addKeyFrame(vals[i], vals[i + 1], vals[i + 2]);
		
		return path;
	}
}
