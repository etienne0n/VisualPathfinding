package com.etienne0n.github.playground;


import static com.etienne0n.github.mathutils.Constants.*;

import com.etienne0n.github.mathutils.Constants;

class Tile implements Comparable<Tile>{
	private final int x;
	private final int y;
	/**
	 * current distance from start vertex/tile
	 */
	private int gDistance = INITIAL_DISTANCE;
	/**
	 * estimated distance to target vertex/tile.
	 * IMPORTANT!: <br>
	 * Must never be larger than the actual distance to target.<br>
	 * If 'squared distances' are used, gDistance must also be squared.
	 * 
	 */
	private int hDistance = INITIAL_DISTANCE;
	
	private Tile predecessor = null;
	
	
	
	public Tile(int x, int y) {
		if(x < 0 || y < 0) {
			throw new IllegalArgumentException("Ordinates must not be smaller than zero!");
		}
		this.y = y;
		this.x = x;
	}
	
	
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void resetTile() {
		resetDistances();
	}
	
	public void clearPredecessor() {
		predecessor = null;
	}
	public void setPredecessor(Tile predecessor) {
		
		if(predecessor != null) {
			int xDiff = Math.abs(predecessor.getX() - getX());
			int yDiff = Math.abs(predecessor.getY() - getY());
			
			if(xDiff > 1 || yDiff > 1) {
				throw new IllegalArgumentException("Predecessor must be a neighbor cell.");
			}
			
			this.predecessor = predecessor;
		}
		
		
	}
	public Tile getPredecessor() {
		return predecessor;
	}
	
	public int getGDistance() {
		return gDistance;
	}

	public int getHDistance() {
		return hDistance;
	}

	public void resetDistances() {
		hDistance = Constants.INITIAL_DISTANCE;
		gDistance = Constants.INITIAL_DISTANCE;
	}
	public void setHDistanceTo(Tile target) {
		
		int targetX = target.getX();
		int targetY = target.getY();
		
		int deltaX = Math.abs(x - targetX);
		int deltaY = Math.abs(y - targetY);
		
		
		/*
		 * Edit 06.03.2021
		 * 
		 * alternative calculation: squared distances (pythagoras)
		 *
		 */
		
		
		
		
		int deltaDeltas = Math.abs(deltaY - deltaX);
		
		if(deltaX == 0 || deltaY == 0) {
			hDistance = (deltaX + deltaY) * HORIZONTAL_VERTICAL_DISTANCE;
		}else if(deltaX == deltaY) {
			hDistance = deltaX * DIAGONAL_DISTANCE;
		}else {
			hDistance = deltaX < deltaY ? 
					deltaX * DIAGONAL_DISTANCE + deltaDeltas * HORIZONTAL_VERTICAL_DISTANCE :
						deltaY * DIAGONAL_DISTANCE + deltaDeltas * HORIZONTAL_VERTICAL_DISTANCE;
		}
		
		
		
		
		
		
	}
	
	
	
	public void setGDistance(int gDistance) {
		this.gDistance = gDistance;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	
	public int getFDistance() {
		return gDistance + hDistance;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tile other = (Tile) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public int compareTo(Tile o) {
		return Integer.compare(getFDistance(), o.getFDistance());
	}
	
	@Override
	public String toString() {
		return "[" + x + ", " + y + "]";
	}
	
	
}	
