import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;

public class ZoneJeu extends JComponent {
	private static final long serialVersionUID = 1L;
	private ArrayList<Bille> billes = new ArrayList<Bille>(); 
	private ArrayList<Point> pos = new ArrayList<Point>(); 	
	int largeur = 350;
	int longueur = 350;

	public ZoneJeu() {}

	public Dimension getPreferredSize() {
		return new Dimension(largeur,longueur);
	}
	
	public void paint(Graphics g) {
		for (int i = 0 ; i < billes.size() ; i++) {
			g.translate(pos.get(i).x, pos.get(i).y);
			billes.get(i).paint(g);
			g.translate(-pos.get(i).x, -pos.get(i).y);
		}
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, largeur-1, longueur-1);	
	}
	
	public void add(Bille bille, Point p) {
		add(bille);
		billes.add(bille);
		pos.add(p);
	}
	
}
