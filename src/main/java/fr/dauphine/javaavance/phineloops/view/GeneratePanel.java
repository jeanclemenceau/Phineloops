package fr.dauphine.javaavance.phineloops.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import fr.dauphine.javaavance.phineloops.programs.Generator;

public class GeneratePanel extends JPanel{
	
	private static final long serialVersionUID = 7466667841865132170L;

	private final JTextField widthField = new JTextField();
	private final JTextField heightField = new JTextField();
	private final JLabel errorWarning = new JLabel("");
	
	public GeneratePanel() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBackground(Color.LIGHT_GRAY);
		
		widthField.setText("  6  ");
		heightField.setText("  6  ");
		
		JPanel inputPanel = new JPanel();
		inputPanel.setBackground(Color.LIGHT_GRAY);
		inputPanel.add(widthField);
		inputPanel.add(new JLabel("x"));
		inputPanel.add(heightField);
		
		errorWarning.setForeground(Color.red);
		
		JButton generateButton = new JButton("Generate");
		generateButton.setBackground(Color.white);
		generateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					new MainDisplay(Generator.generateGrid(Integer.parseInt(widthField.getText().trim()), Integer.parseInt(heightField.getText().trim())));
					((JFrame) SwingUtilities.getRoot((Component) e.getSource())).dispose();
				}
				catch(Exception ex) {
					errorWarning.setText("Only numbers please");
				}
			}
		});
		
		add(inputPanel);
		add(generateButton);
		add(errorWarning);
	}
}
