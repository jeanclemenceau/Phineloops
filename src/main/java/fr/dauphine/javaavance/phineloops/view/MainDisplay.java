package fr.dauphine.javaavance.phineloops.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.dauphine.javaavance.phineloops.model.Grid;

public class MainDisplay extends JFrame {

	private static final long serialVersionUID = 1L;
	private Grid grid;
	
	public MainDisplay(Grid g) {
		super();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(new Dimension(960, 720));
		setTitle("Phineloops");
		setIconImage(new ImageIcon(getClass().getResource("/images/icon.png")).getImage());
		
		grid = g;
		JPanel mainContainer = new JPanel();
		JPanel titlePanel = new JPanel();
		JPanel contentPanel = new JPanel();
		
		contentPanel.add(new GridDisplay(grid));
		contentPanel.add(Box.createRigidArea(new Dimension(20,0)));
		contentPanel.add(new ActionsDisplay(grid));
		contentPanel.setBackground(Color.DARK_GRAY);
		
		titlePanel.setMaximumSize(new Dimension(960, 20));
		titlePanel.setBackground(Color.LIGHT_GRAY);
		titlePanel.add(new JLabel("PhineLoops"));
		
		mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
		mainContainer.setBackground(Color.DARK_GRAY);
		mainContainer.add(titlePanel);
		mainContainer.add(Box.createRigidArea(new Dimension(0,20)));
		mainContainer.add(contentPanel);
		
		add(mainContainer);
		setVisible(true);
	}
	
}
