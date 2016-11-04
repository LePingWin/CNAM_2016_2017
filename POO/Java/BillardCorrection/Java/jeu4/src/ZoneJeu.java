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
	Terrain terrain = null;
	private ArrayList<Bille> billes = new ArrayList<Bille>(); 
	private ArrayList<Point> pos = new ArrayList<Point>(); 	
	int largeur = 350;
	int longueur = 350;

	public ZoneJeu(Terrain terrain) {
		// ajoute une bille par défaut
		add(new Bille(Color.red,30), new Point(10,10));
		this.terrain = terrain;
	}
	public Dimension getPreferredSize() {
		return new Dimension(largeur,longueur);
	}
	
	public void paint(Graphics g) {
		terrain.paint(g);
		for (int i = 0 ; i < billes.size() ; i++) {
			g.translate(pos.get(i).x, pos.get(i).y);
			billes.get(i).paint(g);
			g.translate(-pos.get(i).x, -pos.get(i).y);
		}
	}
	
	public void add(Bille bille, Point p) {
		billes.add(bille);
		pos.add(p);
	}
	
}
