package fr.dauphine.javaavance.phineloops.model;

import java.util.HashMap;
import java.util.Map;

public class Piece {
	private int orientationMax;
	private int pieceNumber;
	private int pieceOrientation;
	private int nbConnections;
	private boolean fixed = false;
	private int x, y;
	
	public static Map<Integer, Integer[]> correspondence = new HashMap<Integer, Integer[]>(); // give orientationMax and nbConnections for a pieceNumber
	
	static {
		correspondence.put(0, new Integer[] {0,0}); 
		correspondence.put(1, new Integer[] {3,1}); // max orientation 3 max connections 2
		correspondence.put(2, new Integer[] {1,2});
		correspondence.put(3, new Integer[] {3,3});
		correspondence.put(4, new Integer[] {0,4});
		correspondence.put(5, new Integer[] {3,2});
	}
	
	public Piece(int num, int orientation) {
		pieceNumber = num;
		pieceOrientation = orientation;
		orientationMax = correspondence.get(num)[0];
		nbConnections = correspondence.get(num)[1];
	}
	
	public Piece(int num, int orientation, int x, int y) {
		pieceNumber = num;
		pieceOrientation = orientation;
		orientationMax = correspondence.get(num)[0];
		nbConnections = correspondence.get(num)[1];
		this.x = x;
		this.y = y;
	}
	
	/***
	 * Pivot the piece by 90° (clockwise) 
	 */
	public void pivot() {
		pieceOrientation = (pieceOrientation+1) % (orientationMax+1);
	}

	public int getOrientationMax() {
		return orientationMax;
	}

	public int getPieceOrientation() {
		return pieceOrientation;
	}

	public int getNbConnections() {
		return nbConnections;
	}

	public int getPieceNumber() {
		return pieceNumber;
	}
}
