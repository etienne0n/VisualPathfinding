package com.etienne0n.github.values;
import static com.etienne0n.github.mathutils.Constants.ROBOT_VELOCITY;
public enum Direction {
	NORTH(0.0, -ROBOT_VELOCITY), 
	SOUTH(0.0, ROBOT_VELOCITY), 
	WEST(-ROBOT_VELOCITY, 0.0), 
	EAST(ROBOT_VELOCITY, 0.0);
	
	
	private final double xVel;
	private final double yVel;
	Direction(double xVel, double yVel) {
		this.xVel = xVel;
		this.yVel = yVel;
	}
	
	public double getXVel() {
		return xVel;
	}
	public double getYVel() {
		return yVel;
	}
}
