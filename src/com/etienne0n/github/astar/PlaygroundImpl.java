package com.etienne0n.github.astar;

import java.util.ArrayList;
import java.util.List;

class PlaygroundImpl implements Playground {
	
	private final boolean [][] playground;
	private final Tile [][] tileField;
	
	private final int width;
	private final int height;
	
	PlaygroundImpl(boolean [][] playground){
		this.playground = playground;
		width = playground[0].length;
		height = playground.length;
		
		tileField = new Tile[height][width];
		
		/*
		 * Building the tileField. For every cell in playground that is true make a new Tile with the same coordinates.
		 * For every cell in playground that is false add null at the same coordinates in tileField.
		 */
	
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tileField[y][x] = playground[y][x] ? new Tile(x, y) : null;
			}
		}

		
	}
	
	@Override
	public int width() {
		return width;
	}

	@Override
	public int height() {
		return height;
	}
	
	@Override
	public boolean getValue(int x, int y) {
		if(x < 0 || x > width + 1 || y < 0 || y > height + 1) {
			StringBuilder sb = new StringBuilder();
			sb.append("\nThe values of the ordinates must not be out of bounds of the playground.\n");
			sb.append("The playground has the width: ").append(width).append(",\nand the height: ").append(height);
			sb.append("\nYou tried to access the field: ").append("[").append(x).append(",").append(y).append("].");
			throw new IllegalArgumentException(sb.toString());
		}else {
			return playground[y][x];
		}
		
		
	}

	@Override
	public List<Integer[]> shortestPath(int row1, int col1, int row2, int col2) {
		
		if(row1 >= height || row2 >= height || col1 >= width || col2 >= width) {
			throw new IllegalArgumentException("The coordinates you want to access are out of bounds of this playground.");
		}
		
		/*
		 * Switching to and from, so the returned list will be in the 
		 * correct order.
		 */
		
		Tile to = tileField[row1][col1];
		Tile from = tileField[row2][col2];
		
		
		
		AStar aStar = new AStar(tileField, from, to);
		if(!aStar.shortestPath()) {
			return null;
		}
		List<Integer[]> retList = new ArrayList<>();
		//System.out.println("AStar finished!");
		Tile currentTile = to;
		
		while(!currentTile.getPredecessor().equals(currentTile)) {
			Tile predecessor = currentTile.getPredecessor();
			//retList.add(predecessor);
			
			Integer[] iAry = {new Integer(predecessor.getX()), new Integer(predecessor.getY())};
			retList.add(iAry);
			
			currentTile = predecessor;
		}
		
		
		return retList;
		
	}

	@Override
	public boolean path(int row1, int col1, int row2, int col2) {
		if(row1 >= height || row2 >= height || col1 >= width || col2 >= width) {
			throw new IllegalArgumentException("The coordinates you want to access are out of bounds of this playground.");
		}
		
		Tile from = tileField[row1][col1];
		Tile to = tileField[row2][col2];
		
		AStar aStar = new AStar(tileField, from, to);
		return aStar.shortestPath();
		
	}





}
