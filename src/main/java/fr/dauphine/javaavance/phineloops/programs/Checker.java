package fr.dauphine.javaavance.phineloops.programs;

import fr.dauphine.javaavance.phineloops.model.Grid;
import fr.dauphine.javaavance.phineloops.model.Piece;
import fr.dauphine.javaavance.phineloops.model.PieceProperties;

/**
 * This class embed the process of checking the resolution of a grid
 *
 */
public class Checker {

	/**
	 * Check a grid
	 * Explore the grid cell by cell and verify if the connections of corresponding piece and its neighbors are well plugged
	 * @param grid the grid to check
	 * @return a boolean : true if the grid is resolved / false if the grid is not resolved
	 * 
	 * @see Grid
	 * @see Piece
	 */
	public static boolean check(Grid grid) {
		int[] connections;
		Piece p;
		Piece[] neighbours;
		int[] neighbourLinks;
		Piece currentN;
		
		for(int i = 0; i<grid.getHeight(); i++) {
			for(int j=0; j<grid.getWidth(); j++) {
				p = grid.getPieces()[j][i];
				neighbours = grid.getPieceNeighbours(p);
				connections = PieceProperties.getLinksOnCardinalPoints(p.getNum(), p.getOrientation());
				
				for(int k = 0; k< connections.length; k++) {
					if(connections[k]>0) {
						currentN = neighbours[k];
						if(currentN==null)
							return false;
						neighbourLinks = PieceProperties.getLinksOnCardinalPoints(currentN.getNum(), currentN.getOrientation());
						if(neighbourLinks[(k+2)%4]!=connections[k])
							return false;
						}
					}
				}
		}
		return true;
	}

}
