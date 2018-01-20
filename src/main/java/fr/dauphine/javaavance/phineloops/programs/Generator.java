package fr.dauphine.javaavance.phineloops.programs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import fr.dauphine.javaavance.phineloops.model.Grid;
import fr.dauphine.javaavance.phineloops.model.Piece;
import fr.dauphine.javaavance.phineloops.model.PieceProperties;

public class Generator {
	public static Grid generateGrid(int width, int height) {
		Grid g = new Grid(width, height);
		Piece[][] pieces = g.getPieces();
		Random randomizer = new Random();
		Set<Integer> allowedValues;
		Integer[] values;

		for(int i = 0; i < height; i++)
			for(int j = 0; j < width; j++) {
				allowedValues = getAllowedValues(j, i, width, height);
				values = allowedValues.toArray(new Integer[allowedValues.size()]);
				// TODO
				// retirer des possibilités les pieces en fonction des pieces deja générées
				pieces[j][i] = new Piece(values[randomizer.nextInt(values.length)], 0, j, i);
			}

		g.shuffle();

		return g;
	}

	public static Set<Integer> getAllowedValues(int x, int y, int width, int height){
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
