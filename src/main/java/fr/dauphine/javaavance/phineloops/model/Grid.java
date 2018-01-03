package fr.dauphine.javaavance.phineloops.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Grid {
	private int width;
	private int height;
	private List<Piece> pieces = new ArrayList<>();
	
	public Grid(String fileName) throws Exception {
		File f = new File(fileName);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String line;
			String[] data;
			
			width = Integer.parseInt(reader.readLine());
			height = Integer.parseInt(reader.readLine());	
			
			for(int i = 0; i<height; i++) {
				for(int j = 0; j<width; j++) {
					line = reader.readLine();
					if(!line.equals(null)) {
						data = line.split(" ");
						pieces.add(new Piece(Integer.parseInt(data[0]), Integer.parseInt(data[1]), j, i));
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
			String ls = System.getProperty("line.separator");
			StringBuilder builder = new StringBuilder(width + ls + height);
			
			for (Piece piece : pieces) 
				builder.append(ls + piece.getNum() + " " + piece.getOrientation());
			
			writer.write(builder.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	public List<Piece> getPieces() {
		return pieces;
	}
	
}
