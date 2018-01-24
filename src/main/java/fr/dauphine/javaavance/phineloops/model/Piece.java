package fr.dauphine.javaavance.phineloops.model;

import java.util.Observable;

public class Piece extends Observable{
	private int num;
	private int orientation;
	private int orientationMax;
	private int nbConnections;
	private boolean fixed = false;
	private int x, y;
	
	public Piece(int num, int orientation) {
		this.num = num;
		this.orientation = orientation;
		orientationMax = PieceProperties.getIdentifier(num).getOrientationMax();
		nbConnections = PieceProperties.getIdentifier(num).getNbConnections();
	}

	public Piece(int num, int orientation, int x, int y) {
		this.num = num;
		this.orientation = orientation;
		this.x = x;
		this.y = y;
		orientationMax = PieceProperties.getIdentifier(num).getOrientationMax();
		nbConnections = PieceProperties.getIdentifier(num).getNbConnections();
	}
	
	/***
	 * Pivot the piece by 90° (clockwise) 
	 */
	public void pivot() {
		if(!fixed) {
			orientation = (orientation+1) % (orientationMax+1);
			notifyObservers();
		}
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
		return PieceProperties.getUnicode(num, orientation);
	}

	@Override
	public String toString() {
		return "Piece [num=" + num + ", orientation=" + orientation + "]";
	}
}
