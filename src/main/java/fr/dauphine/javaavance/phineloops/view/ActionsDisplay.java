package fr.dauphine.javaavance.phineloops.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import fr.dauphine.javaavance.phineloops.model.Grid;

public class ActionsDisplay extends JPanel{
	
	private Grid grid;
	
	private static final long serialVersionUID = 1L;

	public ActionsDisplay(Grid g) {
		grid = g;
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(250, 300));
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		add(Box.createRigidArea(new Dimension(0,50)));
		add(new GeneratePanel());
		add(Box.createRigidArea(new Dimension(0,50)));
		add(new CheckPanel(grid));
		add(Box.createRigidArea(new Dimension(0,50)));
		
	}

}
