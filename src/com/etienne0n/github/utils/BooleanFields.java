package com.etienne0n.github.utils;

import java.util.ArrayList;
import java.util.List;

import com.etienne0n.github.playground.Playground;
import com.etienne0n.github.playground.Playgrounds;

/**
 * A utility class for 2D boolean arrays.<br>
 *
 * @author Etienne Onasch
 *
 */
public final class BooleanFields {
	private BooleanFields() {
	}
	/**
	 * Number of blocks = number of all cells in the field / DIVISOR
	 */
	private static final int DIVISOR = 2;
	private static final double BIAS = 0.9; // must be 0.0 <= BIAS <= 1.0

	private static boolean T = true;
	private static boolean F = false;
	
	
	


	private static final boolean[] PARCOUR_ROW_0 = { T, T, T, T, T, T, T, T };
	private static final boolean[] PARCOUR_ROW_1 = { F, F, F, F, F, F, F, T };
	private static final boolean[] PARCOUR_ROW_2 = PARCOUR_ROW_0;
	private static final boolean[] PARCOUR_ROW_3 = { T, F, F, F, F, F, F, F };
	private static final boolean[] PARCOUR_ROW_4 = PARCOUR_ROW_0;
	private static final boolean[] PARCOUR_ROW_5 = PARCOUR_ROW_1;
	private static final boolean[] PARCOUR_ROW_6 = PARCOUR_ROW_0;
	private static final boolean[] PARCOUR_ROW_7 = PARCOUR_ROW_0;
	/**
	 * 8x8 Field<br>
	 * <br>
	 * 
	 * Sketch:<br>
	 * ------------------------------------ <br>
	 * <b><tt>
	 * . = true 
	 * <br>
	 * F = false 
	 * <br></b></tt> ------------------------------------ <br>
	 * <blockquote><b><tt>
	 * . . . . . . . . <br>
	 * F F F F F F F . <br>
	 * . . . . . . . . <br>
	 * . F F F F F F F <br>
	 * . . . . . . . . <br>
	 * F F F F F F F . <br>
	 * . . . . . . . . <br>
	 * . . . . . . . . <br>
	 * </blockquote></b></tt>
	 * 
	 * ------------------------------------ <br>
	 * 
	 */
	public static final boolean[][] PARCOUR_FIELD_8x8 = { PARCOUR_ROW_0, PARCOUR_ROW_1, PARCOUR_ROW_2, PARCOUR_ROW_3,
			PARCOUR_ROW_4, PARCOUR_ROW_5, PARCOUR_ROW_6, PARCOUR_ROW_7 };

	private static final boolean[] OBSTACLE_ROW_0 = { T, T, T, T, F, T, T, T };
	private static final boolean[] OBSTACLE_ROW_1 = { F, T, T, T, T, T, T, F };
	private static final boolean[] OBSTACLE_ROW_2 = { F, T, F, F, F, F, T, T };
	private static final boolean[] OBSTACLE_ROW_3 = { T, T, T, T, T, T, T, T };
	private static final boolean[] OBSTACLE_ROW_4 = { T, F, F, T, F, F, F, T };
	private static final boolean[] OBSTACLE_ROW_5 = { T, F, T, T, T, F, T, T };
	private static final boolean[] OBSTACLE_ROW_6 = { T, F, T, F, T, T, T, T };
	private static final boolean[] OBSTACLE_ROW_7 = { T, T, T, F, T, T, T, F };
	/**
	 * 8x8 Field<br>
	 * <br>
	 * 
	 * Sketch:<br>
	 * ------------------------------------ <br>
	 * <b><tt>
	 * . = true 
	 * <br>
	 * F = false 
	 * <br></b></tt> ------------------------------------ <br>
	 * <blockquote><b><tt>
	 * . . . . . F . . <br>
	 * F . . . . . . F <br>
	 * F . F F F F . . <br>
	 * . . . . . . . . <br>
	 * . F F . F F F .<br>
	 * . F . . . F . .<br>
	 * . F . F . . . . <br>
	 * . . . F . . . F<br>
	 * </blockquote></b></tt>
	 * 
	 * ------------------------------------ <br>
	 * 
	 */
	public static final boolean[][] OBSTACLE_FIELD_8x8 = { OBSTACLE_ROW_0, OBSTACLE_ROW_1, OBSTACLE_ROW_2,
			OBSTACLE_ROW_3, OBSTACLE_ROW_4, OBSTACLE_ROW_5, OBSTACLE_ROW_6, OBSTACLE_ROW_7 };

	// 16 * 8

	private static final boolean[] WIDE_ROW_0 = { T, T, T, T, T, T, F, T, F, T, F, F, F, T, T, T };
	private static final boolean[] WIDE_ROW_1 = { T, T, T, T, T, T, F, T, T, T, T, T, T, T, F, T };
	private static final boolean[] WIDE_ROW_2 = { T, T, F, F, T, T, F, T, T, F, F, T, F, F, F, T };
	private static final boolean[] WIDE_ROW_3 = { T, F, F, T, T, T, F, T, T, F, F, T, F, T, F, T };
	private static final boolean[] WIDE_ROW_4 = { T, T, F, T, F, T, T, T, T, F, T, T, F, T, T, T };
	private static final boolean[] WIDE_ROW_5 = { T, T, F, T, F, T, F, F, F, F, F, F, F, F, F, T };
	private static final boolean[] WIDE_ROW_6 = { T, T, F, T, T, T, T, T, F, T, T, F, F, T, F, T };
	private static final boolean[] WIDE_ROW_7 = { T, T, F, T, T, T, F, T, T, T, T, T, T, T, T, T };

	/**
	 * 16x8 Field<br>
	 * <br>
	 * 
	 * Sketch:<br>
	 * ------------------------------------ <br>
	 * <b><tt>
	 * . = true 
	 * <br>
	 * F = false 
	 * <br></b></tt>
	 * ------------------------------------------------------------------------ <br>
	 * <blockquote><b><tt>
	 * . . . . . . F . F . F F F . . .<br>
	 * . . . . . . F . . . . . . . F .<br>
	 * . . F F . . F . . F F . F F F .<br>
	 * . F F . . . F . . F F . F . F .<br>
	 * . . F . F . . . . F . . F . . .<br>
	 * . . F . F . F F F F F F F F F .<br>
	 * . . F . . . . . F . . F F . F .<br>
	 * . . F . . . F . . . . . . . . .<br>
	 * </blockquote></b></tt>
	 * 
	 * ------------------------------------------------------------------------ <br>
	 * 
	 */
	public static final boolean[][] WIDE_FIELD_16x8 = { WIDE_ROW_0, WIDE_ROW_1, WIDE_ROW_2, WIDE_ROW_3, WIDE_ROW_4,
			WIDE_ROW_5, WIDE_ROW_6, WIDE_ROW_7, };

	private static final boolean[] WIDE2_ROW_0 = { T, T, T, T, T, T, T, F, T, F, T, F, T, F, T, F };
	private static final boolean[] WIDE2_ROW_1 = { T, F, F, T, F, F, T, T, T, T, T, T, T, T, T, F };
	private static final boolean[] WIDE2_ROW_2 = { T, F, T, T, T, F, T, F, T, F, F, F, F, T, F, F };
	private static final boolean[] WIDE2_ROW_3 = { T, F, T, T, T, F, T, T, T, F, T, T, F, T, T, F };
	private static final boolean[] WIDE2_ROW_4 = { T, F, T, F, T, F, T, T, F, F, T, T, T, T, T, T };
	private static final boolean[] WIDE2_ROW_5 = { T, F, T, T, T, F, T, T, T, T, T, F, F, F, F, T };
	private static final boolean[] WIDE2_ROW_6 = { T, F, F, T, F, F, T, F, F, T, T, T, T, T, F, T };
	private static final boolean[] WIDE2_ROW_7 = { T, T, T, T, T, T, T, F, F, T, T, F, T, T, T, T };

	/**
	 * 16x8 Field<br>
	 * <br>
	 * 
	 * Sketch:<br>
	 * ------------------------------------ <br>
	 * <b><tt>
	 * . = true 
	 * <br>
	 * F = false 
	 * <br></b></tt>
	 * ------------------------------------------------------------------------ <br>
	 * <blockquote><b><tt>
	 * . . . . . . . F . F . F . F . F<br>
	 * . F F . F F . . . . . . . . . F<br>
	 * . F . . . F . F . F F F F . F F<br>
	 * . F . . . F . . . F . . F . . F<br>
	 * . F . F . F . . F F . . . . . .<br>
	 * . F . . . F . . . . . F F F F .<br>
	 * . F F . F F . F F . . . . . F .<br>
	 * . . . . . . . F F . . F . . . .<br>
	 * </blockquote></b></tt>
	 * 
	 * ------------------------------------------------------------------------ <br>
	 * 
	 */
	public static final boolean[][] WIDE_FIELD_2_16x8 = { WIDE2_ROW_0, WIDE2_ROW_1, WIDE2_ROW_2, WIDE2_ROW_3,
			WIDE2_ROW_4, WIDE2_ROW_5, WIDE2_ROW_6, WIDE2_ROW_7, };
	
	
	
	
	private static final boolean[] MEDIUM_FIELD_1_ROW_0 = {T,T,F,T,F,F,F,F,T,F,T,T};
	private static final boolean[] MEDIUM_FIELD_1_ROW_1 = {T,T,F,T,T,F,T,F,T,F,T,F};
	private static final boolean[] MEDIUM_FIELD_1_ROW_2 = {T,F,F,F,T,T,T,T,T,F,T,F};
	private static final boolean[] MEDIUM_FIELD_1_ROW_3 = {T,T,F,T,T,F,T,T,T,F,T,T};
	private static final boolean[] MEDIUM_FIELD_1_ROW_4 = {T,T,T,T,F,F,F,T,F,F,F,T};
	private static final boolean[] MEDIUM_FIELD_1_ROW_5 = {T,F,F,T,T,F,T,T,T,T,F,T};
	private static final boolean[] MEDIUM_FIELD_1_ROW_6 = {T,F,F,T,F,F,F,T,F,T,T,T};
	private static final boolean[] MEDIUM_FIELD_1_ROW_7 = {T,T,T,T,T,T,T,T,F,T,F,T};
	
	/**
	 * 12x8 Field<br>
	 * <br>
	 * 
	 * Sketch:<br>
	 * ------------------------------------ <br>
	 * <b><tt>
	 * . = true 
	 * <br>
	 * F = false 
	 * <br></b></tt>
	 * ------------------------------------------------------------------------ <br>
	 * <blockquote><b><tt>
	 * . . F . F F F F . F . . <br>
	 * . . F . . F . F . F . F <br>
	 * . F F F . . . . . F . F <br>
	 * . . F . . F . . . F . . <br>
	 * . . . . F F F . F F F . <br>
	 * . F F . . F . . . . F . <br>
	 * . F F . F F F . F . . . <br>
	 * . . . . . . . . F . F . <br>
	 * </blockquote></b></tt>
	 * 
	 * ------------------------------------------------------------------------ <br>
	 * 
	 */
	public static final boolean[][] MEDIUM_FIELD_1_12x8 = {
			MEDIUM_FIELD_1_ROW_0,
			MEDIUM_FIELD_1_ROW_1,
			MEDIUM_FIELD_1_ROW_2,
			MEDIUM_FIELD_1_ROW_3,
			MEDIUM_FIELD_1_ROW_4,
			MEDIUM_FIELD_1_ROW_5,
			MEDIUM_FIELD_1_ROW_6,
			MEDIUM_FIELD_1_ROW_7};
	
	
	public static final boolean[][] getRandomField_8x8(){
		return getRandomField(8, 8);
	}
	public static final boolean[][] getRandomField_12x8(){
		return getRandomField(8, 12);
	}
	
	public static final boolean[][] getRandomField_16x8(){
		return getRandomField(8, 16);
	}
	public static final boolean[][] getRandomField_20x12(){
		return getRandomField(12,20);
	}
	/**
	 * For a resolution of 1920x1080 and SPRITE_STANDARD_HEIGHT = 42 
	 * (mathutils.Constants)
	 */
	public static final boolean[][] getRandomField_45x20(){
		return getRandomField(20,45);
	}
	
	public static final boolean[][] getRandomField_60x30(){
		return getRandomField(30,60);
	}
	
	/*
	 * Creation of a random field with usage of the astar algorithm
	 */
	/**
	 * Procedural field generation of a given size
	 * @param rows the number of rows
	 * @param columns the number of columns
	 * @return a boolean 2D array (used semantics: false := 'wall'; true := 'grass')
	 */
	private static final boolean[][] getRandomField(int rows, int columns) {
//		assert (BIAS >= 0 && BIAS <= 1.0); 
		
		// Numbers of fields that are already in use, or must not be used.
		List<Integer> usedOrForbidden = new ArrayList<>();

		// init the field with only 'grass'
		boolean[][] field = new boolean[rows][columns];
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < columns; c++) {
				field[r][c] = true; 
			}
		}
		
	
		int fields = rows * columns;
		// number of blocks := false cells is 1/Divisor of rows * columns. False cells are 'walls'.
		int blocks = fields / DIVISOR; 
		
		blocks: // very oldschool java.
		while (blocks > 0) {
			
			int nextBlock;
			

			do {
				
				nextBlock = (int) (Math.random() * (fields - 1 + BIAS));
				/*
				 * Try until constraint is met. 
				 * In this case it always terminates but it is not cost efficient!
				 * Idea: Datastructure only with allowed cells that get updated every circle.
				 */
			} while (usedOrForbidden.contains(nextBlock));
		
			/*
			 * the number of a field (0-128) is 16 * y-value + x-value. (number of columns =
			 * 16) Therefore in the other direction if you have the number 35 for example
			 * it's 2 * 16 + 3
			 * 
			 * how often does 16 fit into 35? 2 times -> index of rows what is the rest? 3
			 * -> index of columns
			 * 
			 */
			int nextBlockY = nextBlock / columns;
			int nextBlockX = nextBlock % columns;
			
			// get surrounding true cells

			List<Integer> surroundingTrueCells = surroundingTrueCells(nextBlockX, nextBlockY, field);
			
			
			
			// Additional constraint *************************************************************************
			
			
			// Use this if you dont want constructions like this:
			// [T][F]
			// [F][T]
			// but only like this:
			// [T][F] 
			// [F][F] 
			// (walls must not touch only at their corners)
			// current implementation works but is not cost efficient.


			
			
//			// get surrounding false cells			
//			List<Integer> surroundingFalseCells = surroundingFalseCells(nextBlockX, nextBlockY, field);
//			// All possible diagonal neighbors
//			int ne = nextBlock - columns + 1;
//			int nw = nextBlock - columns - 1;
//			int se = nextBlock + columns + 1;
//			int sw = nextBlock + columns - 1;
//			
//			// All possible non-diagonal neighbors
//			
//			int n = nextBlock - columns;
//			int s = nextBlock + columns;
//			int e = nextBlock + 1;
//			int w = nextBlock - 1;
//			
//
//			boolean noDiagonalsConstraint = (surroundingFalseCells.isEmpty())
//					|| (		
//					(surroundingFalseCells.contains(ne) ? surroundingFalseCells.contains(n) || surroundingFalseCells.contains(e) : true)
//					&&
//					(surroundingFalseCells.contains(nw) ? surroundingFalseCells.contains(n) || surroundingFalseCells.contains(w) : true)
//					&&
//					(surroundingFalseCells.contains(se) ? surroundingFalseCells.contains(s) || surroundingFalseCells.contains(e) : true) 
//					&&
//					(surroundingFalseCells.contains(sw) ? surroundingFalseCells.contains(s) || surroundingFalseCells.contains(w) : true)
//					);
//			if(!noDiagonalsConstraint) {
//				continue;
//			}
			
			
			
			
			//************************************************************************************************
			
			// is there a path between all those true cells that are separated by nextBlock? 
			// this ensures the main constraint: all grass tiles (true cells) must be reachable from each other!

			// is there more than one surrounding true cell?
			boolean path = true;
			if (surroundingTrueCells.size() > 1) {
				// get and remove the first cell
				int firstCell = surroundingTrueCells.remove(0);
				// first, what is the x-pos of the first cell?
				int firstCellX = firstCell % columns;
				// and the y-pos?
				int firstCellY = firstCell / columns;
				
				// set next block (temporarily) 
				field[nextBlockY][nextBlockX] = false;
				
				// now check if there is a path to the other surrounding true cells
				// create current playground
				Playground current = Playgrounds.generatePlayground(field);
				
			for (int c : surroundingTrueCells) {
					int cX = c % columns;
					int cY = c / columns;
				
					if (!current.path(firstCellY, firstCellX, cY, cX)) {
						field[nextBlockY][nextBlockX] = true;

						path = false;
						usedOrForbidden.add(columns * nextBlockY + nextBlockX);
						continue blocks; // very oldschool java.
						
					}
				} 
			
				if(path) {
					blocks--;
				}
				
				usedOrForbidden.add(columns * nextBlockY + nextBlockX);
				
			} else {

				field[nextBlockY][nextBlockX] = false;
				usedOrForbidden.add(columns * nextBlockY + nextBlockX);
				blocks--;

			}
			
		}
		
		return field;
	}

	/**
	 * Returns all cells around a certain cell in a boolean 2D array<br>
	 * that are true.<br>
	 * The cell(s) are returned as a just List of integers.<br>
	 * To calculate their real coordinates you just need the number of colums of the
	 * field.<br>
	 * For example:<br>
	 * <br>
	 * <b>
	 * {@code List<Integer> cellNumbers = surroundingTrueFields(5, 2, aBooleanField);}<br>
	 * {@code int numberOfColums = aBooleanField[0].length;}<br>
	 * {@code // Coordinates of the first field number if cellNumbers.size() > 0}<br>
	 * {@code int x = fieldNumbers.get(0) % numberOfColums;}<br>
	 * {@code int y = fieldNumbers.get(0) / numberOfColums;}<br>
	 * </b>
	 * 
	 * @param x
	 *            the x-ordinate
	 * @param y
	 *            the y-ordinate
	 * @param field
	 *            the boolean 2D field
	 * @return a list of integers with a size of 0 or more (can be empty), or
	 *         <b>{@code null}</b> if the given coordinates are not in the boolean
	 *         2D field.
	 */
	public static final List<Integer> surroundingTrueCells(int x, int y, boolean[][] field) {
		return surroundingCells(x, y, field, true);
	}

	/**
	 * Returns all cells around a certain cell in a boolean 2D array<br>
	 * that are false.<br>
	 * The cell(s) are returned just as a List of integers.<br>
	 * To calculate their real coordinates you just need the number of colums of the
	 * field.<br>
	 * For example:<br>
	 * <br>
	 * <b>
	 * {@code List<Integer> cellNumbers = surroundingFalseFields(5, 2, aBooleanField);}<br>
	 * {@code int numberOfColums = aBooleanField[0].length;}<br>
	 * {@code // Coordinates of the first field number if cellNumbers.size() > 0}<br>
	 * {@code int x = fieldNumbers.get(0) % numberOfColums;}<br>
	 * {@code int y = fieldNumbers.get(0) / numberOfColums;}<br>
	 * </b>
	 * 
	 * @param x
	 *            the x-ordinate
	 * @param y
	 *            the y-ordinate
	 * @param field
	 *            the boolean 2D field
	 * @return a list of integers with a size of 0 or more (can be empty), or
	 *         <b>{@code null}</b> if the given coordinates are not in the boolean
	 *         2D field.
	 */
	public static final List<Integer> surroundingFalseCells(int x, int y, boolean[][] field) {
		return surroundingCells(x, y, field, false);
	}

	/**
	 * Returns a list of cell numbers that belong to all the cells around a certain
	 * cell number that are false in a boolean 2D field
	 * 
	 * @param cellNumber
	 *            the cell which false neighbors you want to get
	 * @param field
	 *            the boolean 2D field
	 * @see for an explanation what a cell number is, take a look at the javadoc of
	 *      the methods <br>
	 *      <b>{@code surroundingFalseCells(int x, int y, boolean [][] field)}</b>
	 *      or <br>
	 *      <b>{@code surroundingTrueCells(int x, int y, boolean [][] field)}</b>
	 * @return a list of cell numbers as integers or <b>{@code null}</b> if the
	 *         given cell number is not in the field
	 */
	public static final List<Integer> surroundingFalseCells(int cellNumber, boolean[][] field) {
		return surroundingFalseCells(cellNumber % field[0].length, cellNumber / field[0].length, field);
	}

	/**
	 * Returns a list of cell numbers that belong to all the cells around a certain
	 * cell number that are true in a boolean 2D field
	 * 
	 * @param cellNumber
	 *            the cell which true neighbors you want to get
	 * @param field
	 *            the boolean 2D field
	 * @see for an explanation what a cell number is, take a look at the javadoc of
	 *      the methods <br>
	 *      <b>{@code surroundingFalseCells(int x, int y, boolean [][] field)}</b>
	 *      or <br>
	 *      <b>{@code surroundingTrueCells(int x, int y, boolean [][] field)}</b>
	 * @return a list of cell numbers as integers or <b>{@code null}</b> if the
	 *         given cell number is not in the field
	 */
	public static final List<Integer> surroundingTrueCells(int cellNumber, boolean[][] field) {
		return surroundingTrueCells(cellNumber % field[0].length, cellNumber / field[0].length, field);
	}

	private static final List<Integer> surroundingCells(int x, int y, boolean[][] field, boolean cellValue) {
		List<Integer> cellNumbers = new ArrayList<>();
		// fieldsize
		int height = field.length; // rows
		int width = field[0].length; // columns

		// are the given coordinates in the field?
		if (x < 0 || x > width - 1 || y < 0 || y > height - 1) {
			return null;
		}

		int minX = x - 1 < 0 ? 0 : x - 1;
		int maxX = x + 1 >= width ? width - 1 : x + 1;
		int minY = y - 1 < 0 ? 0 : y - 1;
		int maxY = y + 1 >= height ? height - 1 : y + 1;
		

		for (int r = minY; r <= maxY; r++) {
			for (int c = minX; c <= maxX; c++) {
				
				// not same cell
				if (r != y || c != x) {
					if (field[r][c] == cellValue) {
						cellNumbers.add(width * r + c);
					}
				}
			}
		}

		return cellNumbers;
	}

//	public static void main(String[] args) {
//		boolean[][] field = { { false, true, false }, { true, false, true }, { false, true, false } };
//
//		int cellNumber = 4;
//
//		List<Integer> trueCellNumbers = surroundingTrueCells(cellNumber, field);
//		List<Integer> falseCellNumbers = surroundingFalseCells(cellNumber, field);
//		System.out.println("surrounding true cells:");
//		for (Integer i : trueCellNumbers) {
//			System.out.println("[x:" + i % field[0].length + ",y:" + i / field[0].length + "]");
//		}
//		System.out.println("surrounding false cells:");
//		for (Integer i : falseCellNumbers) {
//			System.out.println("[x:" + i % field[0].length + ",y:" + i / field[0].length + "]");
//		}
//
//	}

}
