package fr.dauphine.javaavance.phineloops.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.dauphine.javaavance.phineloops.model.Piece;

public class ClickOnPieceController implements ActionListener {
	
	private final Piece p;

	public ClickOnPieceController(Piece p) {
		this.p = p;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		p.pivot();	
	}

}
