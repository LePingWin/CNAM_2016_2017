package donnee;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

public class Bille {
	
	Color couleur = Color.yellow;
	private int taille = 10;
	private int id = 0;
	static int compteur = 0;
	
	public Bille(Color color, int taille) {
		setTaille(taille);
		couleur = color;
		id = compteur; 
		compteur++;
	}
	public void paint(Graphics g) {
		g.setColor(couleur);
		g.fillOval(0, 0, getTaille(), getTaille());
		g.setColor(Color.BLACK);
		g.drawOval(0, 0, getTaille(), getTaille());
		if (id != 10)
			g.drawString((new Integer(id)).toString(), 15, 15);
	}
	public void setTaille(int size) {
		this.taille = size;
	}
	public int getTaille() {
		return taille;
	}
	public double getRayon() {
		return Math.sqrt(2.)*getTaille()/2.;
	}

}
