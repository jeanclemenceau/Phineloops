package fr.dauphine.javaavance.phineloops.view;


import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;

import fr.dauphine.javaavance.phineloops.model.Grid;
import fr.dauphine.javaavance.phineloops.model.Piece;

public class GridDisplay extends JPanel {

	private static final long serialVersionUID = 1L;
	Grid g;
	public GridDisplay(Grid g) {
		this.g = g;
		setPreferredSize(new Dimension(600, 600));
		Piece[][] pieces = g.getPieces();
		if(g.getHeight()==0 && g.getWidth() ==0) setLayout(new GridLayout());
		else setLayout(new GridLayout(g.getHeight(), g.getWidth()));
		
		for(int i = 0; i<g.getHeight(); i++)
			for(int j = 0; j<g.getWidth(); j++) {
				add(new PieceDisplay(pieces[j][i]));
			}
		
		setVisible(true);
	}

}
