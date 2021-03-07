package com.etienne0n.github.playground;

import static com.etienne0n.github.mathutils.Constants.DIAGONAL_DISTANCE;
import static com.etienne0n.github.mathutils.Constants.HORIZONTAL_VERTICAL_DISTANCE;

import java.util.HashSet;
import java.util.Set;

import com.etienne0n.github.mathutils.BinaryMinHeap;

class AStar{
	
	//private final Tile from;
	private final Tile to;
	private Tile[][]field;
	
	private final int width;
	private final int height;
	
	private Set<Tile> closedList = new HashSet<>();
	private BinaryMinHeap<Tile> openList = new BinaryMinHeap<>();
	
	AStar(Tile[][] field, Tile from, Tile to) {
		/*
		 * Starting node is its own predecessor. 
		 */
		//this.from = from;
		this.to = to;
		this.field = field;
		this.width = field[0].length;
		this.height = field.length;
		
		
		from.setPredecessor(from);
		//from.setHDistanceTo(to); // not necessary
		from.setGDistance(0);
		openList.add(from);
	}
	
	public boolean shortestPath(){
		do {
			
			Tile currentTile = openList.removeMin();
			if(currentTile.equals(to)) {
				return true;
			}
			closedList.add(currentTile);
			
			expandTile(currentTile);
			
		}while(!openList.isEmpty());
		
		return false;
	}
	
	private void expandTile(Tile predecessor){ 
		
		int xTile = predecessor.getX();
		int yTile = predecessor.getY();
		
		int lowerXBound = xTile == 0 ? xTile : xTile - 1;
		int lowerYBound = yTile == 0 ? yTile : yTile - 1;
		int upperXBound = xTile == width - 1 ? xTile : xTile + 1;
		int upperYBound = yTile == height - 1 ? yTile : yTile + 1;
	
		for(int y = lowerYBound; y <= upperYBound; y++) {
			for(int x = lowerXBound; x <= upperXBound; x++) {
				
				boolean sameTile = y == yTile && x == xTile;
				boolean diagonal = y != yTile && x != xTile;
				
				if(diagonal || sameTile || field[y][x] == null) {continue;}
				
			
				if(!closedList.contains(field[y][x])) {
					Tile tile = field[y][x];
					
					int tentativeG = predecessor.getGDistance() + (diagonal ? DIAGONAL_DISTANCE : HORIZONTAL_VERTICAL_DISTANCE);
					
					if(openList.contains(tile) && tentativeG >= tile.getGDistance()) {
						continue;
					}
					
					tile.setPredecessor(predecessor);
					tile.setGDistance(tentativeG);
					tile.setHDistanceTo(to);
			
					
					if(openList.contains(tile)) {
						//Knoten hat neuen (niedrigeren) Wert. Heap muss neu geordnet werden.
						openList.decreaseKey(tile);
					} else {
						openList.add(tile);
					}
				}
			}
		}
	
		
		
	}
	
	
	
	
	
	
	
	

}
