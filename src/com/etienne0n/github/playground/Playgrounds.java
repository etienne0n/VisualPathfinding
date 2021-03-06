package com.etienne0n.github.playground;
/**
 * A factory class for instances of Playground.
 * @author Etienne Onasch
 *
 */
public final class Playgrounds {
	private Playgrounds() {}
	/**
	 * Generates an instance of Playground with the given boolean 2D array.<br>
	 * 
	 * 
	 * 
	 * @param field the boolean 2D array
	 * @return an instance of Playground
	 */
	public static Playground generatePlayground(boolean [][] field) {
		if(field == null || field.length == 0|| field[0].length == 0) {
			throw new IllegalArgumentException("Parameter must not be null. Neither the number of rows nor the number of columns must be zero.");
		}
		return new PlaygroundImpl(field);
	}
	
	
	
}
