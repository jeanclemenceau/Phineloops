package fr.dauphine.javaavance.phineloops.programs;

import java.util.Random;
import java.util.Set;

import fr.dauphine.javaavance.phineloops.model.Grid;
import fr.dauphine.javaavance.phineloops.model.Piece;

public class Generator {
	/***
	 * Generate a random grid
	 * @param width width of the requested grid
	 * @param height height of the requested grid
	 * @return a solvable random grid
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
