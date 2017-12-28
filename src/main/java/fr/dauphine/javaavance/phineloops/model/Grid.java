package fr.dauphine.javaavance.phineloops.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Grid {
	private int width;
	private int height;
	private List<Piece> pieces;
	
	public Grid(String fileName) throws Exception {
		File f = new File(fileName);
		try {
			FileInputStream fis = new FileInputStream(f);
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
			String s;
			String[] line;
			
			width = Integer.parseInt(reader.readLine());
			height = Integer.parseInt(reader.readLine());	
			
			for(int i = 0; i<height; i++) {
				for(int j = 0; j<width; j++) {
					s = reader.readLine();
					if(!s.equals(null)) {
						line = s.split(" ");
						pieces.add(new Piece(Integer.parseInt(line[0]), Integer.parseInt(line[1]), j, i));
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

	public List<Piece> getPieces() {
		return pieces;
	}	
	
}
