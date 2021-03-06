package com.etienne0n.github.mathutils;

public final class Constants {
	private Constants() {}
	
	public static final int INITIAL_DISTANCE = 0;
	public static final int HORIZONTAL_VERTICAL_DISTANCE = 100;
	public static final int DIAGONAL_DISTANCE = 141;
	public static final int DISTANCE_DIFF = Math.abs(HORIZONTAL_VERTICAL_DISTANCE - DIAGONAL_DISTANCE);
	public static final double ROBOT_VELOCITY = 650.0;
	
	
	public static final int SPRITE_STANDARD_HEIGHT = 30; // standard 42
	public static final int SPRITE_STANDARD_WIDTH = SPRITE_STANDARD_HEIGHT;
	
}
