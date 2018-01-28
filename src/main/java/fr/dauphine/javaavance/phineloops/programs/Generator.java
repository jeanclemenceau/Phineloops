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
	 * Generate a random grid solved
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

		return g;
	}

	/**
	 * Generate a random solvable grid
	 * @param  int width         the width of the grid
	 * @param  int height        the height of the grid
	 * @return     a solvable grid
	 * @see Grid
	 */
	public static Grid generate(int width, int height){
		Grid g = generateGrid(width, height);
		g.shuffle();
		return g;
	}


	/**
	 * Generate a solvable grid with a set number of connected components
	 * @param  int width         the width of the grid
	 * @param  int height        the height of the grid
	 * @param  int nbcc          the wanted number of connected components
	 * @return     a solvable grid with nbcc connected components
	 * @see Grid
	 * @see Piece
	 */
	public static Grid generateGridWithNbcc(int width, int height, int nbcc) throws IllegalArgumentException{
		// no more nbcc than (width*height)/3 we could use (width*height)/2 but we want to avoid long computing time
		if(nbcc > (width*height)/3) throw new IllegalArgumentException("nbcc : Number of connected components is too high for the grid's size");
		Grid g;
		int nb;
		do{
			g = generateGrid(width, height);
			nb = g.colorGrid();
		}while(nb<nbcc);
		if(nb == nbcc) {g.shuffle(); return g;}
		Piece[][] pieces = g.getPieces();
		for(int i = 0; i < height; i++)
			for(int j = 0; j < width; j++) {
				if(pieces[j][i].getColor()>nbcc){
					pieces[j][i].setNum(0);
					pieces[j][i].setOrientation(0);
				}
			}

		g.shuffle();
		return g;
	}

}
