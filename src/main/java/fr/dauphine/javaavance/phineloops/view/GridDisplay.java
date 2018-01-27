package fr.dauphine.javaavance.phineloops.view;


import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;

import fr.dauphine.javaavance.phineloops.model.Grid;
import fr.dauphine.javaavance.phineloops.model.Piece;

/**
 * This class is the panel hosting all the pieces composing a grid for a graphic display
 * @see JPanel
 */
public class GridDisplay extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * <p>
	 * For each piece of the grid it create a new PieceDisplay and add it its panel
	 * </p>
	 * @param g
	 * 			The grid to display in a graphic way
	 * @see PieceDisplay
	 * @see Piece
	 */
	public GridDisplay(Grid g) {
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
