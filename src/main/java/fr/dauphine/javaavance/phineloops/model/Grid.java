package fr.dauphine.javaavance.phineloops.model;

import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Grid {
	private int width;
	private int height;
	private Piece[][] pieces;
	private List<Piece> piecesFixed;

	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		pieces = new Piece[width][height];
		piecesFixed = new ArrayList<Piece>();
	}

	/***
	 * Construct a new Grid from a file which respect the accurate format
	 * @param fileName
	 * @throws Exception
	 */
	public Grid(String fileName) throws Exception {
		File f = new File(fileName);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String line;
			String[] data;

			width = Integer.parseInt(reader.readLine());
			height = Integer.parseInt(reader.readLine());
			pieces = new Piece[width][height];
			piecesFixed = new ArrayList<Piece>();


			int number;
			for(int i = 0; i<height; i++) {
				for(int j = 0; j<width; j++) {
					line = reader.readLine();
					if(!line.equals(null)) {
						data = line.split(" ");
						number = Integer.parseInt(data[0]);
						pieces[j][i] = new Piece(number, Integer.parseInt(data[1]),j,i);
						if(number == 0 || number == 4) {
							pieces[j][i].setFixed(true);
							piecesFixed.add(pieces[j][i]);
						}
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

	/***
	 * Stores the grid in a flat file, respect the accurate format
	 * @param fileName
	 */
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

	/***
	 * Retrieves neighbours (adjacent pieces) for a piece
	 * @param p the piece for which we want to know its neighbours
	 * @return an array of Piece with index : 0 -> north, 1 -> east, 2 -> south, 3 -> west
	 */
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

	/***
	 * Retrieves the neighbours for each piece of the grid
	 * @return a map with each key is a piece and corresponding value is its neighbours
	 */
	public HashMap<Piece, Piece[]> getAllNeighbours(){
		HashMap<Piece,Piece[]> allNeighbours = new HashMap<Piece, Piece[]>();
		for(int i=0; i<height; i++) {
			for(int j=0; j<width; j++) {
				allNeighbours.put(pieces[j][i], getPieceNeighbours(pieces[j][i]));
			}
		}
		return allNeighbours;
	}


	/***
	 * Method that indicates the possible pieces for a case of the grid
	 * @param  int  x             abscissa
	 * @param  int  y             ordinate
	 * @return      a set of possible pieces
	 */
	public Set<Piece> getPossiblePieces(int x, int y){
		Set<Integer> allowedValues = getAllowedValues(x, y);
		Set<Piece> possiblePieces = new HashSet<Piece>();
		Piece up;
		Piece left;
		if(x != 0)
			left = pieces[x-1][y];
		else
			left = new Piece(0,0);
		if(y != 0)
			up = pieces[x][y-1];
		else
			up = new Piece(0,0);

		int nbCoUp = PieceProperties.getLinksOnCardinalPoints(up.getNum(),up.getOrientation())[2];
		int nbCoLeft = PieceProperties.getLinksOnCardinalPoints(left.getNum(),left.getOrientation())[1];
		int[] links;

		for(Integer i : allowedValues){
			for(int j = 0; j<=new Piece(i,0,x,y).getOrientationMax(); j++){
				links = PieceProperties.getLinksOnCardinalPoints(i,j);

				if(links[0] == nbCoUp && links[3] == nbCoLeft){
					//for pieces not on the right nor down edges of the grid
					if(x<width-1 && y < height-1){
						possiblePieces.add(new Piece(i,j,x,y));
					}

					//for pieces on the down edge of the grid except the bottom right piece
					else if(y == height-1 && links[2] == 0 && x!= width-1){
						possiblePieces.add(new Piece(i,j,x,y));
					}

					//for pieces on the right edge of the grid
					else if(x == width-1 && links[1]==0 && (y != height-1 || links[2] == 0)){
						possiblePieces.add(new Piece(i,j,x,y));
					}
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
	 * @return Set of piece numbers for a piece position on the grid
	 */
	public Set<Integer> getAllowedValues(int x, int y){
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

	public boolean isAllowedOrientation(Piece p, List<Piece> s) {
		int x = p.getX();
		int y = p.getY();
		Piece[] neighbours = getPieceNeighbours(p);
		int[] links = PieceProperties.getLinksOnCardinalPoints(p.getNum(), p.getOrientation());
		int[] neighbourLinks;

		if(y==0 && links[0]!=0) return false;
		if(x==0 && links[3]!=0) return false;
		if(y==height-1 && links[2]!=0) return false;
		if(x==width-1 && links[1]!=0) return false;
		for (int i = 0; i<neighbours.length; i++) {
			if(s.contains(neighbours[i])) {
				neighbourLinks = PieceProperties.getLinksOnCardinalPoints(neighbours[i].getNum(), neighbours[i].getOrientation());
				if(links[i]!=neighbourLinks[(i+2)%4]) {
					return false;
				}
			}
		}
		return true;
	}

	/***
	 * Prints the grid in Unicode
	 */
	public void print() {
		for(int i= 0; i< height; i++) {
			for(int j=0; j<width; j++) {
				System.out.print(pieces[j][i].toUnicode());
				if(j == width-1) System.out.println();
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

	public void setPieces(int x, int y, Piece p){
		pieces[x][y] = p;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public List<Piece> getPiecesFixed(){
		return piecesFixed;
	}
}
