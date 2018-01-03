package fr.dauphine.javaavance.phineloops.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Piece {
	private int num;
	private int orientation;
	private int orientationMax;
	private int nbConnections;
	private boolean fixed = false;
	private int x, y;
	
	public static Map<Integer, Integer[]> correspondence = new HashMap<>(); // give orientationMax and nbConnections for a pieceNumber
	
	static {
		correspondence.put(0, new Integer[] {0,0}); 
		correspondence.put(1, new Integer[] {3,1}); // max orientation 3 max connections 1
		correspondence.put(2, new Integer[] {1,2});
		correspondence.put(3, new Integer[] {3,3});
		correspondence.put(4, new Integer[] {0,4});
		correspondence.put(5, new Integer[] {3,2});
	}
	
	public static Map<List<Integer>, String> unicodeMatch = new HashMap<>();
	
	static {
		unicodeMatch.put(Arrays.asList(new Integer[] {0,0}), "\u2205");
		unicodeMatch.put(Arrays.asList(new Integer[] {1,0}), "\u2579");
		unicodeMatch.put(Arrays.asList(new Integer[] {1,1}), "\u257A");
		unicodeMatch.put(Arrays.asList(new Integer[] {1,2}), "\u257B");
		unicodeMatch.put(Arrays.asList(new Integer[] {1,3}), "\u2578");
		unicodeMatch.put(Arrays.asList(new Integer[] {2,0}), "\u2503");
		unicodeMatch.put(Arrays.asList(new Integer[] {2,1}), "\u2501");
		unicodeMatch.put(Arrays.asList(new Integer[] {3,0}), "\u253B");
		unicodeMatch.put(Arrays.asList(new Integer[] {3,1}), "\u2523");
		unicodeMatch.put(Arrays.asList(new Integer[] {3,2}), "\u2533");
		unicodeMatch.put(Arrays.asList(new Integer[] {3,3}), "\u252B");
		unicodeMatch.put(Arrays.asList(new Integer[] {4,0}), "\u254B");
		unicodeMatch.put(Arrays.asList(new Integer[] {5,0}), "\u2517");
		unicodeMatch.put(Arrays.asList(new Integer[] {5,1}), "\u250F");
		unicodeMatch.put(Arrays.asList(new Integer[] {5,2}), "\u2513");
		unicodeMatch.put(Arrays.asList(new Integer[] {5,3}), "\u251B");
	}
	
	public Piece(int num, int orientation) {
		this.num = num;
		this.orientation = orientation;
		orientationMax = correspondence.get(num)[0];
		nbConnections = correspondence.get(num)[1];
	}
	
	public Piece(int num, int orientation, int x, int y) {
		this.num = num;
		this.orientation = orientation;
		orientationMax = correspondence.get(num)[0];
		nbConnections = correspondence.get(num)[1];
		this.x = x;
		this.y = y;
	}
	
	/***
	 * Pivot the piece by 90° (clockwise) 
	 */
	public void pivot() {
		if(!fixed) 
			orientation = (orientation+1) % (orientationMax+1);
		else
			System.out.println("This piece is fixed. It can't rotate.");
	}

	public int getOrientationMax() {
		return orientationMax;
	}

	public int getOrientation() {
		return orientation;
	}

	public int getNbConnections() {
		return nbConnections;
	}

	public int getNum() {
		return num;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String toUnicode() {
		return unicodeMatch.get(Arrays.asList(new Integer[] {num, orientation}));
	}

	@Override
	public String toString() {
		return "Piece [num=" + num + ", orientation=" + orientation + "]";
	}
}
