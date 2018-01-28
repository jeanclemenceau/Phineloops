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

/**
 * A Panel for the user to perform the checking action to see if the grid he played on is resolved or not
 * @see JPanel
 */
public class CheckPanel extends JPanel {

	private static final long serialVersionUID = 4864668343980907677L;
	
	/**
	 * A label for displaying wether the current grid is solved or not
	 * @see JLabel
	 */
	private final JLabel solvedInfo = new JLabel("                                     ");
	
	/**
	 * The current grid displayed on the graphic interface
	 * @see Grid
	 */
	private final Grid grid;
	
	/**
	 * Constructor of the check panel
	 * <p>
	 * Create a button for listening user action and label to display the resulting message
	 * </p>
	 * @param g
	 * 			the grid displayed on the gui
	 */
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
