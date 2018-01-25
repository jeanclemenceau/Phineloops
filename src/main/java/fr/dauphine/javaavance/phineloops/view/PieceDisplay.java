package fr.dauphine.javaavance.phineloops.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import fr.dauphine.javaavance.phineloops.model.Piece;

public class PieceDisplay extends JButton implements Observer {


	private static final long serialVersionUID = 1L;
	private final Piece p;
	private Image image;
	
	public PieceDisplay(final Piece p) {
		this.p = p;
		
		setBackground(Color.WHITE);

		this.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				p.pivot();
				System.out.println(p.toString());
			}
		});
		
		p.addObserver(this);
	}
	
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    
	    if(PieceDisplay.class.getResource(getImage(p.getNum(),p.getOrientation()))!=null)
			image = new ImageIcon(PieceDisplay.class.getResource(getImage(p.getNum(),p.getOrientation()))).getImage();

	    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
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
			if(orientation == 0) return "/images/1-0.png";
			if(orientation == 1) return "/images/1-1.png";
			if(orientation == 2) return "/images/1-2.png";
			if(orientation == 3) return "/images/1-3.png";
		case 2:
			if(orientation == 0) return "/images/2-0.png";
			if(orientation == 1) return "/images/2-1.png";
		case 3:
			if(orientation == 0) return "/images/3-0.png";
			if(orientation == 1) return "/images/3-1.png";
			if(orientation == 2) return "/images/3-2.png";
			if(orientation == 3) return "/images/3-3.png";
		case 4:
			return "/images/4-0.png";
		case 5:
			if(orientation == 0) return "/images/5-0.png";
			if(orientation == 1) return "/images/5-1.png";
			if(orientation == 2) return "/images/5-2.png";
			if(orientation == 3) return "/images/5-3.png";
		default:
			return "\u000F";
		}
	}

}
