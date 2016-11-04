package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;
import View.*;

public class ZoneJeu extends JComponent {
	private static final long serialVersionUID = 1L;
	int largeur = 350;
	int longueur = 350;

	public ZoneJeu() {}

	public Dimension getPreferredSize() {
		return new Dimension(largeur,longueur);
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, largeur-1, longueur-1);
	}
}
