package fr.dauphine.javaavance.phineloops.view;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Window;

import javax.swing.JDialog;
import fr.dauphine.javaavance.phineloops.model.Grid;
import fr.dauphine.javaavance.phineloops.model.Piece;

public class GridDisplay extends JDialog {

	private static final long serialVersionUID = 1L;

	public GridDisplay(Grid g) {
		super((Window)null);
		setModal(true);
		
		Piece[][] pieces = g.getPieces();
		
		setSize(new Dimension(1000,1000));		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("PhineLoops");	
		setLayout(new GridLayout(g.getHeight(), g.getWidth()));
		
		for(int i = 0; i<g.getHeight(); i++)
			for(int j = 0; j<g.getWidth(); j++) {
				add(new PieceDisplay(pieces[j][i]));
			}
		
		setVisible(true);
	}

}
