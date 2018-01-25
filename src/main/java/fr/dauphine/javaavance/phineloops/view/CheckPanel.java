package fr.dauphine.javaavance.phineloops.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.dauphine.javaavance.phineloops.model.Grid;
import fr.dauphine.javaavance.phineloops.programs.Checker;

public class CheckPanel extends JPanel {

	private static final long serialVersionUID = 4864668343980907677L;
	private final JLabel solvedInfo = new JLabel("");
	private final Grid grid;
	
	public CheckPanel(Grid g) {
		grid = g;
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBackground(Color.LIGHT_GRAY);
		
		
		solvedInfo.setForeground(Color.LIGHT_GRAY);
		
		JButton checkButton = new JButton("Check");
		checkButton.setBackground(Color.WHITE);
		checkButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean solved = Checker.check(grid);
				if(solved) {
					solvedInfo.setForeground(Color.GREEN);
					solvedInfo.setText("Grid is Solved !");
				}
				else {
					solvedInfo.setForeground(Color.RED);
					solvedInfo.setText("Grid is not Solved !");
				}
			}
		});
		
		add(checkButton);
		add(solvedInfo);
	}

}
