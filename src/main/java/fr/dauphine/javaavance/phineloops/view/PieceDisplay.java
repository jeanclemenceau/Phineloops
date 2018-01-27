package fr.dauphine.javaavance.phineloops.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import controller.ClickOnPieceController;
import fr.dauphine.javaavance.phineloops.model.Piece;
import fr.dauphine.javaavance.phineloops.model.PieceProperties;

/**
 * This class serves as the displayer for a piece of the grid
 * It is a JButton which can be clicked on for interacting with the concerned piece
 *@see JButton
 *@see Observer
 */
public class PieceDisplay extends JButton implements Observer {


	private static final long serialVersionUID = 1L;
	
	/**
	 * The piece associated with this display
	 * @see Piece
	 */
	private final Piece p;
	
	/**
	 * The image fitting the current state of the piece
	 * @see Image
	 */
	private Image image;

	/**
	 * Constructor
	 * <p>
	 * It initializes the p property, tells the referenced piece that the display observes it, and adds as 
	 * the action listener the controller for the referenced piece.
	 * </p>
	 * @param p
	 * 			The piece we want to display
	 * @see Piece
	 * @see ClickOnPieceController
	 */
	public PieceDisplay(final Piece p) {
		this.p = p;
		setBackground(Color.WHITE);
		p.addObserver(this);
		this.addActionListener(new ClickOnPieceController(p));
	}

	/**
	 * Retrieve the image corresponding to the current orientation of the referenced piece and draw it
	 * @see Graphics
	 * @see ImageIcon
	 */
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    if(PieceDisplay.class.getResource(PieceProperties.getImage(p.getNum(),p.getOrientation()))!=null)
			image = new ImageIcon(PieceDisplay.class.getResource(PieceProperties.getImage(p.getNum(),p.getOrientation()))).getImage();

	    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	  }

	/**
	 * Called when subject has called notifyObservers and so perform a repaint for having a displaying matching the current orientation
	 * @see Observable
	 * @see Object
	 */
	@Override
	public void update(Observable o, Object arg) {
		update(getGraphics());
	}

}
