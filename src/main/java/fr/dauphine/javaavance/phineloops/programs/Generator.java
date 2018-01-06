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
		Set<Integer> allowedValues = new HashSet<>();

		for(int i = 0; i < height; i++)
			for(int j = 0; j < width; j++) {
				if(i==0);
			}
		return g;
	}
	
	public static Set<Integer> getAllowedValues(int x, int y, int width, int height){
		Set<Integer> allowedValues = new HashSet<>();
		int[] connectionsOnCardinalPoints;
		boolean remove = true;
		
		for(int i = 0; i < PieceProperties.getNumMax(); i++)
			allowedValues.add(i);
		
		if(x == 0 || x == width-1 || y == 0 || y == height-1)
			for(int i = 0; i < PieceProperties.getNumMax(); i++) {
				connectionsOnCardinalPoints = PieceProperties.getLinksOnCardinalPoints(i, 0);
				for(int j = 0; j < connectionsOnCardinalPoints.length; j++)
					if(connectionsOnCardinalPoints[j] == 0) 
						remove = false;
				if(remove)
					allowedValues.remove(i);
			}
		
		if((x==0||x==width-1) && (y==0||y==height-1));
			//TODO
			//enlever de l'ensemble les numeros correspondant aux pieces 
			// ->  qui ont 3 points cardinaux connecteurs 
			// -> qui ont 2 points cardinaux connecteurs non adjacents
			
		return allowedValues;
	}
}
