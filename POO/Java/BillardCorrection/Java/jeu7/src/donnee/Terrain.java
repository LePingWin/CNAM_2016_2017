package donnee;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Terrain extends JComponent {
	Image img;
	String file = null;
	boolean etirer = false;

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		// si on a chargé une image de fond, on l'affiche
		if (file != null && etirer) {
			// Calcule le ration pour étirer l'image
			float scaleX = (float)getWidth()/(float)img.getWidth(null);
			float scaleY = (float)getHeight()/(float)img.getHeight(null);
			// Créée un opérateur de transformation avec mes critères d'étirements
			AffineTransform transform = AffineTransform.getScaleInstance(scaleX, scaleY);
			// Dessine mon image avec l'étirement
			g2d.drawImage(img, transform, null);
		} 
		// sinon, on affiche le fond tout pret
		else
			g.drawImage(img, 0, 0, null);
	}

	public Terrain() {
		setSize(300, 300);
		img = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
		Graphics crayon = img.getGraphics();
		crayon.setColor(Color.YELLOW);
		crayon.drawRect(0, 0, 300-1, 300-1);
		crayon.setColor(Color.GREEN.brighter().brighter().brighter());
		crayon.fillRect(1, 1, 300-2, 300-2);
	}

	public Dimension getPreferredSize() {
		return new Dimension(300,300);
	}

	public void setImage(String filename, boolean aEtirer) {
		try {
			img = ImageIO.read(new File(filename));
			file = filename;
			etirer = aEtirer;
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
