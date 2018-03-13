package com.etienne0n.github.utils;

/**
 * A utility class for 2D boolean arrays.<br>
 *
 * @author Etienne Onasch
 *
 */
public final class BooleanFields {
	private BooleanFields() {
	}

	private static boolean T = true;
	private static boolean F = false;

	// 8 x 8
	//-------------------------------------------------------------
	// Parcour field

	private static final boolean[] PARCOUR_ROW_0 = { T, T, T, T, T, T, T, T };
	private static final boolean[] PARCOUR_ROW_1 = { F, F, F, F, F, F, F, T };
	private static final boolean[] PARCOUR_ROW_2 = PARCOUR_ROW_0;
	private static final boolean[] PARCOUR_ROW_3 = { T, F, F, F, F, F, F, F };
	private static final boolean[] PARCOUR_ROW_4 = PARCOUR_ROW_0;
	private static final boolean[] PARCOUR_ROW_5 = PARCOUR_ROW_1;
	private static final boolean[] PARCOUR_ROW_6 = PARCOUR_ROW_0;
	private static final boolean[] PARCOUR_ROW_7 = PARCOUR_ROW_0;
	/**
	 * 8x8 Field<br><br>
	 * 
	 * Sketch:<br>
	 * ------------------------------------
	 * <br><b><tt>
	 * . = true 
	 * <br>
	 * F = false 
	 * <br></b></tt>
	 * ------------------------------------
	 * <br>
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
	 * ------------------------------------
	 * <br>
	 * 
	 */
	public static final boolean [][] PARCOUR_FIELD_8x8 = {
			PARCOUR_ROW_0, 
			PARCOUR_ROW_1, 
			PARCOUR_ROW_2, 
			PARCOUR_ROW_3, 
			PARCOUR_ROW_4, 
			PARCOUR_ROW_5, 
			PARCOUR_ROW_6, 
			PARCOUR_ROW_7};
	
	
	private static final boolean[] OBSTACLE_ROW_0 = {T,T,T,T,F,T,T,T};
	private static final boolean[] OBSTACLE_ROW_1 = {F,T,T,T,T,T,T,F};
	private static final boolean[] OBSTACLE_ROW_2 = {F,T,F,F,F,F,T,T};
	private static final boolean[] OBSTACLE_ROW_3 = {T,T,T,T,T,T,T,T};
	private static final boolean[] OBSTACLE_ROW_4 = {T,F,F,T,F,F,F,T};
	private static final boolean[] OBSTACLE_ROW_5 = {T,F,T,T,T,F,T,T};
	private static final boolean[] OBSTACLE_ROW_6 = {T,F,T,F,T,T,T,T};
	private static final boolean[] OBSTACLE_ROW_7 = {T,T,T,F,T,T,T,F};
	/**
	 * 8x8 Field<br><br>
	 * 
	 * Sketch:<br>
	 * ------------------------------------
	 * <br><b><tt>
	 * . = true 
	 * <br>
	 * F = false 
	 * <br></b></tt>
	 * ------------------------------------
	 * <br>
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
	 * ------------------------------------
	 * <br>
	 * 
	 */
	public static final boolean[][] OBSTACLE_FIELD_8x8 = {
			OBSTACLE_ROW_0,
			OBSTACLE_ROW_1,
			OBSTACLE_ROW_2,
			OBSTACLE_ROW_3,
			OBSTACLE_ROW_4,
			OBSTACLE_ROW_5,
			OBSTACLE_ROW_6,
			OBSTACLE_ROW_7,
	};
	
	
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
	 * 16x8 Field<br><br>
	 * 
	 * Sketch:<br>
	 * ------------------------------------
	 * <br><b><tt>
	 * . = true 
	 * <br>
	 * F = false 
	 * <br></b></tt>
	 * ------------------------------------------------------------------------
	 * <br>
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
	 * ------------------------------------------------------------------------
	 * <br>
	 * 
	 */
	public static final boolean[][] WIDE_FIELD_16x8 = {
			WIDE_ROW_0,
			WIDE_ROW_1,
			WIDE_ROW_2,
			WIDE_ROW_3,
			WIDE_ROW_4,
			WIDE_ROW_5,
			WIDE_ROW_6,
			WIDE_ROW_7,
	};
	
	private static final boolean[] WIDE2_ROW_0 = {T,T,T,T,T,T,T,F,T,F,T,F,T,F,T,F};
	private static final boolean[] WIDE2_ROW_1 = {T,F,F,T,F,F,T,T,T,T,T,T,T,T,T,F};
	private static final boolean[] WIDE2_ROW_2 = {T,F,T,T,T,F,T,F,T,F,F,F,F,T,F,F};
	private static final boolean[] WIDE2_ROW_3 = {T,F,T,T,T,F,T,T,T,F,T,T,F,T,T,F};
	private static final boolean[] WIDE2_ROW_4 = {T,F,T,F,T,F,T,T,F,F,T,T,T,T,T,T};
	private static final boolean[] WIDE2_ROW_5 = {T,F,T,T,T,F,T,T,T,T,T,F,F,F,F,T};
	private static final boolean[] WIDE2_ROW_6 = {T,F,F,T,F,F,T,F,F,T,T,T,T,T,F,T};
	private static final boolean[] WIDE2_ROW_7 = {T,T,T,T,T,T,T,F,F,T,T,F,T,T,T,T};
	
	/**
	 * 16x8 Field<br><br>
	 * 
	 * Sketch:<br>
	 * ------------------------------------
	 * <br><b><tt>
	 * . = true 
	 * <br>
	 * F = false 
	 * <br></b></tt>
	 * ------------------------------------------------------------------------
	 * <br>
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
	 * ------------------------------------------------------------------------
	 * <br>
	 * 
	 */
	public static final boolean[][] WIDE_FIELD_2_16x8 = {
			WIDE2_ROW_0,
			WIDE2_ROW_1,
			WIDE2_ROW_2,
			WIDE2_ROW_3,
			WIDE2_ROW_4,
			WIDE2_ROW_5,
			WIDE2_ROW_6,
			WIDE2_ROW_7,
	};

}
