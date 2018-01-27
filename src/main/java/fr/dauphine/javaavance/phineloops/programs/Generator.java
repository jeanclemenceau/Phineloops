package fr.dauphine.javaavance.phineloops.programs;

import java.util.Random;
import java.util.Set;

import fr.dauphine.javaavance.phineloops.model.Grid;
import fr.dauphine.javaavance.phineloops.model.Piece;

/**
 * This class embed the process of the generation of grid
 *
 */
public class Generator {
	
	/**
	 * Generate a random grid
	 * <p>
	 * For each case of the grid it computes a set of pieces which can fit in (depending of the position and the neighbours pieces)
	 * and randomly pick one. The piece picked is so put at the corresponding coordinates.
	 * </p>
	 * @param width width of the requested grid
	 * @param height height of the requested grid
	 * @return a solvable random grid
	 * 
	 * @see Grid
	 * @see Piece
	 * @see Random
	 * @see Set
	 */
	public static Grid generateGrid(int width, int height) {
		Grid g = new Grid(width, height);
		Piece[][] pieces = g.getPieces();
		Random randomizer = new Random();
		Set<Piece> possiblePieces;
		Piece[] values;

		for(int i = 0; i < height; i++)
			for(int j = 0; j < width; j++) {
				possiblePieces = g.getPossiblePieces(j, i);
				values = possiblePieces.toArray(new Piece[possiblePieces.size()] );
				pieces[j][i] = values[randomizer.nextInt(values.length)];
			}

		g.shuffle();

		return g;
	}
}
