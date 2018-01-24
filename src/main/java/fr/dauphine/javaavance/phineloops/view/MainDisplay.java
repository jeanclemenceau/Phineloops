package fr.dauphine.javaavance.phineloops.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.dauphine.javaavance.phineloops.model.Grid;

public class MainDisplay extends JDialog {

	private static final long serialVersionUID = 1L;
	private Grid grid;
	
	public MainDisplay(Grid g) {
		super();
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(new Dimension(960, 690));
		setTitle("Phineloops");
		
		grid = g;
		JPanel mainContainer = new JPanel();
		JPanel titlePanel = new JPanel();
		JPanel contentPanel = new JPanel();
		
		contentPanel.add(new GridDisplay(grid));
		contentPanel.add(new ActionsDisplay(grid));
		contentPanel.setBackground(Color.DARK_GRAY);
		
		titlePanel.setMaximumSize(new Dimension(960, 20));
		titlePanel.setBackground(Color.LIGHT_GRAY);
		titlePanel.add(new JLabel("PhineLoops"));
		
		mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
		
		mainContainer.add(titlePanel);
		mainContainer.add(contentPanel);
		
		add(mainContainer);
		setVisible(true);
	}
	
}
