package fr.dauphine.javaavance.phineloops.programs;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import fr.dauphine.javaavance.phineloops.model.Grid;
import fr.dauphine.javaavance.phineloops.model.Piece;
import fr.dauphine.javaavance.phineloops.model.PieceProperties;

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
				possiblePieces = getPossiblePieces(j, i, width, height, g);
				values = possiblePieces.toArray(new Piece[possiblePieces.size()] );
				pieces[j][i] = values[randomizer.nextInt(values.length)];
			}

		//g.shuffle();

		return g;
	}


	/***
	 * Method that indicates the possible pieces for a case of the grid
	 * @param  int  x             abscissa
	 * @param  int  y             ordinate
	 * @param  int  width         width of the grid
	 * @param  int  height        height of the grid
	 * @param  Grid g             the grid
	 * @return      a set of possible pieces
	 */
	private static Set<Piece> getPossiblePieces(int x, int y, int width, int height, Grid g){
		Set<Integer> allowedValues = getAllowedValues(x, y, width, height);
		Set<Piece> possiblePieces = new HashSet<Piece>();
		Piece up;
		Piece left;
		if(x != 0)
			left = g.getPieces()[x-1][y];
		else
			left = new Piece(0,0);
		if(y != 0)
			up = g.getPieces()[x][y-1];
		else
			up = new Piece(0,0);


		int nbCoUp = PieceProperties.getLinksOnCardinalPoints(up.getNum(),up.getOrientation())[2];
		int nbCoLeft = PieceProperties.getLinksOnCardinalPoints(left.getNum(),left.getOrientation())[1];
		int[] links;

		for(Integer i : allowedValues){
			for(int j = 0; j<=new Piece(i,0).getOrientationMax(); j++){
				links = PieceProperties.getLinksOnCardinalPoints(i,j);
				if(links[0] == nbCoUp && links[3] == nbCoLeft){
					//for pieces not on the right nor down edges of the grid
					if(x<width-1 && y < height-1)
						possiblePieces.add(new Piece(i,j,x,y));

					//for pieces on the down edge of the grid except the bottom right piece
					else if(y == height-1 && links[2] == 0 && x!= width-1)
						possiblePieces.add(new Piece(i,j,x,y));

					//for pieces on the right edge of the grid
					else if(x == width-1 && links[1]==0 && (y != height-1 || links[2] == 0))
						possiblePieces.add(new Piece(i,j,x,y));
				}
			}
		}
		return possiblePieces;

	}

	/***
	 * Get piece numbers, for a position, which are acceptable for making a solvable grid.
	 * This method does not take care of other pieces already put on the grid.
	 * @param x abscissa
	 * @param y ordinate
	 * @param width width of the grid
	 * @param height height of the grid
	 * @return Set of piece numbers for a piece position on the grid
	 */
	private static Set<Integer> getAllowedValues(int x, int y, int width, int height){
		Set<Integer> allowedValues = new HashSet<>();
		int[] connectionsOnCardinalPoints;
		boolean toRemove = true;
		int counter = 0;

		for(int i = 0; i <= PieceProperties.getNumMax(); i++)
			allowedValues.add(i);

		if(x == 0 || x == width-1 || y == 0 || y == height-1) // If we are on an edge
			for(int i = 0; i <= PieceProperties.getNumMax(); i++) {
				toRemove = true;
				connectionsOnCardinalPoints = PieceProperties.getLinksOnCardinalPoints(i, 0);
				for(int j = 0; j < connectionsOnCardinalPoints.length; j++)
					if(connectionsOnCardinalPoints[j] == 0) // We don't remove pieces which have a side with no connection available (we only want to remove "+" scheme if we are on an edge)
						toRemove = false;
				if(toRemove)
					allowedValues.remove(i);
			}

		if((x==0||x==width-1) && (y==0||y==height-1)) // If we are on an angle
			for(int i = 0; i <= PieceProperties.getNumMax(); i++) {
				toRemove = false;
				counter = 0;
				connectionsOnCardinalPoints = PieceProperties.getLinksOnCardinalPoints(i, 0);
				for(int j = 0; j < connectionsOnCardinalPoints.length; j++) {
					if(connectionsOnCardinalPoints[j] > 0) // We count how many side we have connections on, we want to remove pieces with three sides provided with connections
						counter++;
					if(connectionsOnCardinalPoints[j] > 0 && connectionsOnCardinalPoints[(j+2)%connectionsOnCardinalPoints.length] > 0) // If there is two opposites sides with connections
						toRemove = true;
				}
				if(counter == 3 || toRemove)
					allowedValues.remove(i);
			}

		return allowedValues;
	}
}
