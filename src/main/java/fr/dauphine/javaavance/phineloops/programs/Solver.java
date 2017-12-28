package fr.dauphine.javaavance.phineloops.programs;

import java.util.ArrayDeque;
import java.util.Deque;

import fr.dauphine.javaavance.phineloops.model.Grid;
import fr.dauphine.javaavance.phineloops.model.Piece;

public class Solver {
	private Grid grid;
	private Deque<Piece> stack = new ArrayDeque<Piece>();
	
	public Solver(Grid grid) {
		this.grid = grid;
		initStack();
	}
	
	/***
	 * Initialize the stack with all positions for each piece of the grid
	 * @param grid
	 */
	public void initStack(){
		for (Piece piece : grid.getPieces())
			for(int i = 0; i<piece.getOrientationMax(); i++)
				stack.push(new Piece(piece.getPieceNumber(), i));
	}
	
	
}
