package fr.dauphine.javaavance.phineloops.view;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import fr.dauphine.javaavance.phineloops.model.Piece;

public class PieceDisplay extends JButton implements Observer {


	private static final long serialVersionUID = 1L;
	final Piece p;
	JLabel label = new JLabel("");
	ImageIcon image;
	
	public PieceDisplay(final Piece p) {
		this.p = p;
		
		setBackground(Color.white);
		setForeground(Color.white);
		
		if(PieceDisplay.class.getResource(getImage(p.getNum(),p.getOrientation()))!=null) {
			label.setIcon(new ImageIcon(new ImageIcon(PieceDisplay.class.getResource(getImage(p.getNum(),p.getOrientation()))).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
		}else label.setText(getImage(p.getNum(),p.getOrientation()));
		
		add(label);
		
		this.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				p.pivot();
				if(PieceDisplay.class.getResource(getImage(p.getNum(),p.getOrientation()))!=null) 
					label.setIcon(new ImageIcon(new ImageIcon(PieceDisplay.class.getResource(getImage(p.getNum(),p.getOrientation()))).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
				else label.setText(getImage(p.getNum(),p.getOrientation()));
			}
		});
		
		p.addObserver(this);
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
	
	public String getImage(int num, int orientation) {
		switch (num) {
		case 0:
			return " ";
		case 1:
			if(orientation == 0) return "\u2579";
			if(orientation == 1) return "\u257A";
			if(orientation == 2) return "\u257B";
			if(orientation == 3) return "\u2578";
		case 2:
			if(orientation == 0) return "\u2503";
			if(orientation == 1) return "\u2501";
		case 3:
			if(orientation == 0) return "\u253B";
			if(orientation == 1) return "\u2523";
			if(orientation == 2) return "\u2533";
			if(orientation == 3) return "\u252B";
		case 4:
			return "\u254B";
		case 5:
			if(orientation == 0) return "\u2517";
			if(orientation == 1) return "\u250F";
			if(orientation == 2) return "\u2513";
			if(orientation == 3) return "\u251B";
		default:
			return "\u000F";
		}
	}



}
