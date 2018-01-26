package fr.dauphine.javaavance.phineloops.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import fr.dauphine.javaavance.phineloops.model.Grid;
import fr.dauphine.javaavance.phineloops.programs.Solver;

public class ActionsDisplay extends JPanel{

	private static final long serialVersionUID = 1L;

	public ActionsDisplay(final Grid g) {
		JButton solverButton = new JButton("Solve");
		solverButton.setBackground(Color.white);
		solverButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Solver.solve(g, g.getPieces()[0][0]);
			}
		});
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(250, 300));
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		add(Box.createRigidArea(new Dimension(0,50)));
		add(new GeneratePanel());
		add(Box.createRigidArea(new Dimension(0,50)));
		add(new CheckPanel(g));
		add(Box.createRigidArea(new Dimension(0,50)));
		add(solverButton);
		add(Box.createRigidArea(new Dimension(0,50)));
	}

}
