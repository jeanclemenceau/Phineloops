package fr.dauphine.javaavance.phineloops.model;

import java.util.Observable;

/**
 * Represent a piece
 *@see Observable
 */
public class Piece extends Observable {

	/**
	 * The number of the piece. ie : its identifier
	 */
	private int num;

	/**
	 * Current orientation of the piece
	 */
	private int orientation;

	/**
	 * The maximum number the orientation of the piece can take
	 */
	private final int maxOrientation;

	/**
	 * Wether the piece is fixed (can't rotate) or not
	 */
	private boolean isFixed = false;

	/**
	 * Abscissa of the piece on a grid
	 */
	private final int x;

	/**
	 * Ordinate of the piece on a grid
	 */
	private final int y;

	/**
	 * Color of the piece
	 * number that indicates in which connected component the piece is
	 */
	private int color;

	/**
	 * Constructor for a piece with no grid directly associated (neither x and y provided)
	 * @param num the piece number
	 * @param orientation the piece orientation
	 */
	public Piece(int num, int orientation) {
		this.num = num;
		this.orientation = orientation;
		color = 0;
		maxOrientation = PieceProperties.getIdentifier(num).getOrientationMax();
		x = 0;
		y = 0;
	}

	/**
	 * Constructor for a piece with its position on a grid
	 * @param num the piece number
	 * @param orientation the piece orientation
	 * @param x abscissa coordinate
	 * @param y ordinate coordinate
	 */
	public Piece(int num, int orientation, int x, int y) {
		this.num = num;
		this.orientation = orientation;
		this.x = x;
		this.y = y;
		color = 0;
		maxOrientation = PieceProperties.getIdentifier(num).getOrientationMax();
	}

	/***
	 * Pivot the piece by 90° (clockwise)
	 */
	public void pivot() {
		if(!isFixed) {
			orientation = (orientation+1) % (maxOrientation+1);
			setChanged();
			notifyObservers();
		}
	}

	public int getColor(){
		return color;
	}

	public void setColor(int n){
		color = n;
	}

	public int getOrientationMax() {
		return maxOrientation;
	}

	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int orientation) {
		while(this.orientation!=orientation) pivot();
	}

	public int getNum() {
		return num;
	}

	public void setNum(int n){
		num = n;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean getFixed(){
		return isFixed;
	}

	public void setFixed(boolean b){
		this.isFixed = b;
	}

	public String toUnicode() {
		return PieceProperties.getUnicode(num, orientation);
	}

	@Override
	public String toString() {
		return "Piece [num=" + num + ", orientation=" + orientation + ", x=" + x + ", y=" + y+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + num;
		result = prime * result + orientation;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Piece other = (Piece) obj;
		if (num != other.num)
			return false;
		if (orientation != other.orientation)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}
