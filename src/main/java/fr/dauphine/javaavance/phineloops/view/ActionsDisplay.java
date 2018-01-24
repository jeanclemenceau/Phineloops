package fr.dauphine.javaavance.phineloops.view;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import fr.dauphine.javaavance.phineloops.model.Grid;

public class ActionsDisplay extends JPanel{
	
	private Grid grid;
	private static final long serialVersionUID = 1L;

	public ActionsDisplay(Grid g) {
		grid = g;
		setBackground(Color.LIGHT_GRAY);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		add(new GeneratorPanel());
		
	}

}
