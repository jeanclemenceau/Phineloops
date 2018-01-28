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

/**
 * A JPanel which contains input fields and an action button in order for a user of the gui to generate a new grid
 * @see JPanel
 *
 */
public class GeneratePanel extends JPanel{

	private static final long serialVersionUID = 7466667841865132170L;

	/**
	 * Text input field for the width of desired grid to generate
	 * @see JTextField
	 */
	private final JTextField widthField = new JTextField();

	/**
	 * Text input field for the height of desired grid to generate
	 * @see JTextField
	 */
	private final JTextField heightField = new JTextField();
	
	/**
	 * Text input field for the height of desired grid to generate
	 * @see JTextField
	 */
	private final JTextField ccField = new JTextField();

	/**
	 * A label for displaying error messages
	 * @see JLabel
	 */
	private final JLabel errorWarning = new JLabel("                                        ");

	/**
	 * Constructor of the generate panel
	 * <p>
	 * The panel consists of the two input text fields followed by the button for generate a new grid which passed parameters.
	 * Text fields are initialized with "w" and "h" for giving user information about what he has to put in
	 * </p>
	 */
	public GeneratePanel() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBackground(Color.LIGHT_GRAY);
		
		widthField.setText("  w  ");
		heightField.setText("  h  ");
		ccField.setText("  nb  ");
		
		JPanel inputPanel = new JPanel();
		inputPanel.setBackground(Color.LIGHT_GRAY);
		inputPanel.add(widthField);
		inputPanel.add(new JLabel("x"));
		inputPanel.add(heightField);
		
		JPanel inputNbccPanel = new JPanel();
		inputNbccPanel.setBackground(Color.LIGHT_GRAY);
		inputNbccPanel.add(new JLabel("(components : "));
		inputNbccPanel.add(ccField);
		inputNbccPanel.add(new JLabel(")"));

		JButton generateButton = new JButton("Generate");
		generateButton.setBackground(Color.white);
		generateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					if((ccField.getText().trim().matches("^[0-9]*$")))
						new MainDisplay(Generator.generateGridWithNbcc(Integer.parseInt(widthField.getText().trim()), Integer.parseInt(heightField.getText().trim()), Integer.parseInt(ccField.getText().trim())));
					else
						new MainDisplay(Generator.generate(Integer.parseInt(widthField.getText().trim()), Integer.parseInt(heightField.getText().trim())));	
					((JFrame) SwingUtilities.getRoot((Component) e.getSource())).dispose();
				}
				catch(NumberFormatException ex) {
					errorWarning.setForeground(Color.red);
					errorWarning.setText("Only numbers please");
				}
				catch(IllegalArgumentException ex) {
					errorWarning.setForeground(Color.red);
					errorWarning.setText("Less compon. please");
				}
			}
		});

		add(inputPanel);
		add(inputNbccPanel);
		add(generateButton);
		add(errorWarning);
	}
}
