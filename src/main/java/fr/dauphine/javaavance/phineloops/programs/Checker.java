package fr.dauphine.javaavance.phineloops.programs;

import fr.dauphine.javaavance.phineloops.model.Grid;
import fr.dauphine.javaavance.phineloops.model.Piece;
import fr.dauphine.javaavance.phineloops.model.PieceProperties;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Checker {

	public static boolean check(Grid grid) {
		int[] connections;
		List<Piece> checked = new ArrayList<Piece>();
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
						else if(!checked.contains(currentN)) {
							neighbourLinks = PieceProperties.getLinksOnCardinalPoints(currentN.getNum(), currentN.getOrientation());
							if(neighbourLinks[(k+2)%4]!=connections[k])
								return false;
						}
					}
				}
				checked.add(p);
			}
		}

		return true;
	}

}
