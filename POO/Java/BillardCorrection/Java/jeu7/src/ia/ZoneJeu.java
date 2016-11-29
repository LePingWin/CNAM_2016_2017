package ia;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;

import donnee.Terrain;
import donnee.Bille;
import ihm.Score;
import app.Jeu;

public class ZoneJeu extends JComponent implements MouseListener, Runnable{
	private Terrain terrain = null;
	private Jeu jeu = null;
	private ArrayList<Bille> billes = new ArrayList<Bille>(); 
	private ArrayList<Point> pos = new ArrayList<Point>(); 
	private ArrayList<Point> pmouv = new ArrayList<Point>(); 


	public ZoneJeu(Jeu jeu, Terrain terrain) {
		addMouseListener(this);
		// ajoute une bille par défaut
		// x=0, y=10
		// (10,100) (50,100) (90,100) (130,100)
		// (30,140) (70,140) (110,140)
		// (50,180) (90,180)
		// (70,240)
		int x = 60;
		add(new Bille(Color.red,30), new Point( 10+x,  60));
		add(new Bille(Color.red,30), new Point( 50+x,  60));
		add(new Bille(Color.red,30), new Point( 90+x,  60));
		add(new Bille(Color.red,30), new Point(130+x,  60));
		
		add(new Bille(Color.red,30), new Point( 30+x, 100));
		add(new Bille(Color.red,30), new Point( 70+x, 100));
		add(new Bille(Color.red,30), new Point(110+x, 100));
		
		add(new Bille(Color.red,30), new Point( 50+x, 140));
		add(new Bille(Color.red,30), new Point( 90+x, 140));
		
		add(new Bille(Color.red,30), new Point( 70+x, 180));

		add(new Bille(Color.blue,30), new Point( 70+x, 260));
		
		this.terrain = terrain;
		this.jeu = jeu;
	}

	public void paint(Graphics g) {
		terrain.paint(g);
		for (int i = 0 ; i < billes.size() ; i++) {
			g.translate(pos.get(i).x, pos.get(i).y);
			billes.get(i).paint(g);
			g.translate(-pos.get(i).x, -pos.get(i).y);
		}
		g.setColor(Color.BLACK);
		g.drawString(String.valueOf(Score.get()), 20, terrain.getHeight()-20);
	}

	private boolean croisement(Point nouvP, int index) {
		boolean ret = false;
		for (int i = 0 ; i < billes.size() ; i++) {
			if (i==index)
				continue;
			Point p = pos.get(i);
			if (   Point.distance(p.x, p.y, nouvP.x, nouvP.y)<
					(billes.get(i).getRayon()+billes.get(index).getRayon())/2+1){
				int selx = ((new Random()).nextBoolean() == false)?0:1;
				int sely = ((new Random()).nextBoolean() == false)?0:1;
				pmouv.get(i).setLocation(
						pmouv.get(index).getX()+selx, 
						pmouv.get(index).getY()+sely);
				pmouv.get(index).setLocation(
						-pmouv.get(index).getX()/3, 
						-pmouv.get(index).getY()/3);				
				return true;				
			}
		}
		return ret;
	}

	public Dimension getPreferredSize() {
		return terrain.getPreferredSize();
	}

	public void add(Bille bille, Point p) {
		billes.add(bille);
		pos.add(p);
		pmouv.add(new Point(0,0));

	}


	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		Point psouris = new Point(x,y);

		// cherche parmi toutes les billes celle qui convient, 
		// et affecte le mouvement en fonction
		for (int i = 0; i < billes.size(); i++) {
			Bille bille = billes.get(i);
			Point p = pos.get(i);
			// calcule la position du centre de la bille
			Point pcentre = new Point();
			pcentre.x = (int) (p.x + bille.getTaille()/2.);
			pcentre.y = (int) (p.y + bille.getTaille()/2.);
			// si la position du clic de la souris est dans la zone de dessin de la bille
			double rayon = bille.getRayon();
			if (pcentre.distance(psouris)<rayon+2) {
				x = x - pcentre.x;
				y = y - pcentre.y;
				pmouv.get(i).setLocation(-x,-y);
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void run() {
		while (Thread.currentThread() == jeu.getAnimThread()) {

		for (int i = 0 ; i < billes.size() ; i++) {
			Point p = pos.get(i);
			Point m = pmouv.get(i);
			// diminue le déplacement de temps en temps
			if ((new Random()).nextBoolean() &&
					(new Random()).nextBoolean()
					) {
				boolean oneMove = (m.y==0 || m.x==0)?true:false;
				if (m.x>0)
					m.x--;
				else if (m.x<0)
					m.x++;
				if (m.y>0)
					m.y--;
				else if (m.y<0)
					m.y++;
				if ( (m.y==0 || m.x==0) && oneMove )
				{
					m.y=0;
					m.x=0;
				}
			}
			Point nouv = new Point(p.x + m.x, p.y + m.y);
			int margin = billes.get(i).getTaille();

			// vérifie le mouvement en X
			if (nouv.x < 0) {
				m.x = m.x * -1;
				nouv = new Point(p.x + 2*m.x, p.y + 2*m.y);
			}
			else if (nouv.x > terrain.getWidth()-margin) {
				m.x = m.x * -1;
				nouv = new Point(p.x + 2*m.x, p.y + 2*m.y);
			}

			// vérifie le mouvement en Y
			if (nouv.y < 0) {
				m.y = m.y * -1;
				nouv = new Point(p.x + 2*m.x, p.y + 2*m.y);
			}
			else if (nouv.y > terrain.getHeight()-margin) {
				m.y = m.y * -1;
				nouv = new Point(p.x + 2*m.x, p.y + 2*m.y);
			}
			
			if (croisement(nouv,i))
			{
				Score.up();
				nouv = new Point(p.x + 2*m.x, p.y + 2*m.y);
			}

			pos.set(i, nouv);

		}

		repaint();

		try {
			Thread.currentThread().sleep(50);
		}
		catch (InterruptedException e) {}
		}
	}
}
