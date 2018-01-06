package fr.dauphine.javaavance.phineloops.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.HashMap;

public class Grid {
	private int width;
	private int height;
	private Piece[][] pieces;
	
	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		pieces = new Piece[width][height];
	}
	
	public Grid(String fileName) throws Exception {
		File f = new File(fileName);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String line;
			String[] data;
			
			width = Integer.parseInt(reader.readLine());
			height = Integer.parseInt(reader.readLine());	
			pieces = new Piece[width][height];
			
			for(int i = 0; i<height; i++) {
				for(int j = 0; j<width; j++) {
					line = reader.readLine();
					if(!line.equals(null)) {
						data = line.split(" ");
						pieces[j][i] = new Piece(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
					}
					else {
						reader.close();
						throw new Exception("File is not compatible");
					}
				}
			}
			reader.close();
		}
		catch(FileNotFoundException e) {
				e.printStackTrace();
		}
		catch(IOException e) {
				e.printStackTrace();
		}
	}
	
	public void store(String fileName) {
		File f = new File(fileName);
		try {
			FileWriter writer = new FileWriter(f);
			String ls = System.lineSeparator();
			StringBuilder builder = new StringBuilder(width + ls + height);
			for(int i= 0; i< height; i++) {
				for(int j=0; j<width; j++) {
					builder.append(ls + pieces[j][i].getNum() + " " + pieces[j][i].getOrientation());
				}
			}
					
			writer.write(builder.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	//neighbours[]: 0 north, 1 east, 2 south, 3 west
	public Piece[] getPieceNeighbours(Piece p) {
		int x = p.getX();
		int y = p.getY();
		Piece[] neighbours = new Piece[4];
		
		if(y==0)
			neighbours[0] = null;
		else 
			neighbours[0] = pieces[x][y-1];
		
		if(x==width-1) 
			neighbours[1]=null;
		else 
			neighbours[1]=pieces[x+1][y];		
		
		if(y==height-1)
			neighbours[2]= null;
		else 
			neighbours[2] = pieces[x][y+1];
		
		if(x==0) 
			neighbours[3] = null;
		else 
			neighbours[3] = pieces[x-1][y];
	
		return neighbours;
	}
	
	public HashMap<Piece, Piece[]> getAllNeighbours(){
		HashMap<Piece,Piece[]> allNeighbours = new HashMap<Piece, Piece[]>();
		for(int i=0; i<height; i++) {
			for(int j=0; j<width; j++) {
				allNeighbours.put(pieces[j][i], getPieceNeighbours(pieces[j][i]));
			}
		}
		return allNeighbours;
	}
	
	public void print() {
		for(int i= 0; i< height; i++) {
			for(int j=0; j<width; j++) {
				System.out.print(pieces[j][i].toUnicode());
				if(pieces[j][i].getX() == width-1) System.out.println();
			}
		}
	}
	
	/***
	 * Shuffle all pieces in the grid
	 */
	public void shuffle() {
		Random randomizer = new Random();
		Piece p;
		int rotations;
		
		for(int i = 0; i < height; i++)
			for(int j = 0; j < width; j++) {
				p = pieces[j][i];
				if(p.getNum() != 0 && p.getNum() != 4) {
					rotations = randomizer.nextInt(4);
					for(int k = 0; k < rotations; k++)
						p.pivot();
				}
			}
	}

	public Piece[][] getPieces() {
		return pieces;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
}
