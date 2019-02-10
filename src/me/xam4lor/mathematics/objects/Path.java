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
	 * Create a Path (values must be the init position of the point)
	 * @param x
	 * 	Path init x
	 * @param y
	 * 	Path init y
	 */
	public Path(float x, float y) {
		this.path = new HashMap<Float, Vector>();
		this.times = new ArrayList<Float>();
		
		this.addKeyFrame(0f, x, y);
	}
	
	
	/**
	 * Add a key frame
	 * @param time
	 * 	Time of the key frame (seconds)
	 * @param x
	 * 	X position of the key frame
	 * @param y
	 * 	Y position of the key frame
	 */
	public void addKeyFrame(float time, float x, float y) {
		this.path.put(time, new Vector(x, y));
		this.times.add(time);
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
}
