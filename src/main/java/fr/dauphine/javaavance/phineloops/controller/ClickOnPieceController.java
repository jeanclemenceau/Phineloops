package fr.dauphine.javaavance.phineloops.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.dauphine.javaavance.phineloops.model.Piece;

/**
 * Handle an user clic on a PieceDisplay
 *@see ActionListener
 */
public class ClickOnPieceController implements ActionListener {
	
	/**
	 * The target piece
	 * @see Piece
	 */
	private final Piece p;

	/**
	 * Constructor
	 * <p>
	 * Initialize p property
	 * </p>
	 * @param p the piece to reference
	 */
	public ClickOnPieceController(Piece p) {
		this.p = p;
	}
	
	/**
	 * A click on the associated PieceDisplay triggers a 90° clockwise rotation of the referenced piece
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		p.pivot();	
	}

}
