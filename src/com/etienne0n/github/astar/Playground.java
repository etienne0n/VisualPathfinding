package com.etienne0n.github.astar;

import java.util.List;

/**
 * The main purpose of this class is to provide the shortest path from one coordinate to another.<br>
 * You just need a 2D boolean array in which all {@code false} values will be interpreted as impassable terrain, <br>
 * and all {@code true} values as passable.<br>
 * 
 * <b>Example:</b><br>
 * Let's assume you have a 2D boolean array called myBooleanField,
 * then you can simply create a new instance of Playground using the factory method from the class Playgrounds.<br><br>
 * <blockquote><pre>
 * Playground pg = Playgrounds.generatePlayground(myBooleanField); 
 * </blockquote></pre><br>
 * 
 * 
 * @author Etienne Onasch
 *
 */
public interface Playground {
	
	
	/**
	 * The width of the playground
	 * @return the width of the playground
	 */
	public int width();
	/**
	 * The height of the playground
	 * @return the height of the playground
	 */
	public int height();
	
	/**
	 * Returns the value at Coordinate [x,y]
	 * @param x the x ordinate
	 * @param y the y ordinate
	 * @return the value at Coordinate [x,y]
	 */
	public boolean getValue(int x, int y);
	
	/**
	 * Calculates the shortest Path between two coordinates with the A*-Algorithm.<br>
	 * 
	 * @param row1 the row of the first coordinate.
	 * @param col1 the column of the first coordinate.
	 * @param row2 the row of the second coordinate.
	 * @param col2 the column of the second coordinate.
	 * @return
	 */
	public List<Integer[]> shortestPath(int row1, int col1, int row2, int col2);
	
}
