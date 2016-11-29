package View;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;



public class Jeu implements ActionListener, KeyListener {
// TODO dispose + save aux bons endroits

	// TODO Ajouter browse pour le fichier de fond + checkbox
	// Cr�ation de la fen�tre
	private static JFrame frame = new JFrame("Super Jeu de billard");
	// Cr�ation d'un mod�le d'organisation de l'affichage
	private static GridBagConstraints grille = new GridBagConstraints();
	// Cr�ation d'un panel pour contenir l'ensemble des objets
	private static JPanel panel = new JPanel(new GridBagLayout());
	// Cr�ation d'un texte pour le titre
	private static JLabel titre_label = new JLabel("Super Jeu de billard");
	// Cr�ation d'une zone de jeu
	private static Terrain terrainDeJeu = new Terrain();
	public static ZoneJeu zone = new ZoneJeu(terrainDeJeu);
	// Cr�ation de la barre de menus et les menus
	private static JMenuBar menu = new JMenuBar();
	private static JMenu menu_Jeu = new JMenu("Jeu");
	private static JMenuItem menu_Jeu_New = new JMenuItem("Nouvelle Boule");
	private static JMenuItem menu_Jeu_Score = new JMenuItem("High Score");
	private static JMenuItem menu_Jeu_Back = new JMenuItem("Image de fond");
	private static JMenuItem menu_Jeu_Quit = new JMenuItem("Quitter", KeyEvent.VK_Q);
	private static JMenu menu_Aide = new JMenu("Aide");

	public static Score score = new Score();
	
	public static void main(String[] args) {
		// Lance la cr�ation d'une fen�tre via un thread
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	private static void createAndShowGUI() {
		Jeu jeu = new Jeu();
		loadScore();
		
	}
	private static void loadScore() {
		// recharge les scores
		try {	
			FileInputStream fos = new FileInputStream("scores.serial");
			ObjectInputStream ois= new ObjectInputStream(fos);
			// d�s�rialisation : lecture de l'objet depuis le flux d'entr�e
			score = (Score) ois.readObject();
			ois.close();
			fos.close();
		} catch (IOException e) {
			System.err.println("fichier des scores absent ou illisible");
			score = new Score();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			score = new Score();
		} finally {
		}
	}

	public Jeu() {

		createTitle();
		
		// D�finit son action sur un clic sur le bouton de fermeture (la croix "X")
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Affecte les contraintes d'affichage � la grille
		// remplissage horizontal
		grille.fill = GridBagConstraints.HORIZONTAL;
		// position sur la 1ere ligne 
		grille.gridy = 0;
		// Ajoute le titre dans le panel avec les contraintes de positions d�finies pr�c�demment
		panel.add(titre_label, grille);
		// Ajoute la zone de jeu sur la 2e ligne
		grille.gridy = 1;
		panel.add(zone, grille);

		createMenu();

		// Ajoute le panel dans la fenetre
		frame.add(panel);
		
		// Pr�pare la fen�tre � s'afficher
		frame.pack();
		// Agrandit un peu la fenetre
		frame.setSize(new Dimension(frame.getWidth()+50, frame.getHeight()+50));
		// On r�cup�re la taille de l'�cran (la r�solution)
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		// et on place notre fen�tre au milieu
		frame.setLocation((screen.width - frame.getWidth())/2,(screen.height - frame.getHeight())/2);

		// Affiche la fen�tre � l'�cran
		frame.setVisible(true);
		frame.setFocusTraversalKeysEnabled(false);
		frame.addKeyListener(this);
	}
	
	private void createTitle() {
		// Configure le texte du titre en gras et taille 20
		titre_label.setFont(new Font(Font.MONOSPACED,Font.BOLD, 20));
		// Le centre Horizontalement
		titre_label.setHorizontalAlignment(JLabel.CENTER);
	}
	/**
	 *  Cr�ation et remplissage du menu
	 */
	private void createMenu() {
		menu_Jeu_New.addActionListener(this);
		menu_Jeu_Score.addActionListener(this);
		menu_Jeu_Back.addActionListener(this);
		menu_Jeu_Quit.addActionListener(this);
		menu_Jeu.add(menu_Jeu_New);
		menu_Jeu.add(menu_Jeu_Score);
		menu_Jeu.add(menu_Jeu_Back);
		menu_Jeu.add(menu_Jeu_Quit);
		menu.add(menu_Jeu);
		menu.add(menu_Aide);
		frame.setJMenuBar(menu);
	}

	private PreferencesPanel pref = new PreferencesPanel(this);
	private BackgroundPanel image = new BackgroundPanel(this);
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if ( e.getSource() == menu_Jeu_New ) {
			pref.setVisible(true);
			pref.setLocationRelativeTo(panel);
		}
		else if (e.getSource() == menu_Jeu_Score) {
			JOptionPane.showMessageDialog(frame, 
				score,
				"SCORES",
				JOptionPane.INFORMATION_MESSAGE
				);
		}
		else if ( e.getSource() == menu_Jeu_Quit ) {
			close();
		}
		else if (e.getSource() == menu_Jeu_Back) {
			image.setVisible(true);
			image.setLocationRelativeTo(panel);
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			close();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}

	public void ajouteBille(Color color, int taille) {
		Bille bille = new Bille(color, taille);
		Point p = new Point((new Random()).nextInt(300),(new Random()).nextInt(300));
		zone.add(bille,p);
		zone.repaint();
	}

	private void close() {
		Jeu.save();
		frame.dispose();
		System.exit(0);
	}
	public static void save() {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("scores.serial");
			// cr�ation d'un "flux objet" avec le flux fichier
			ObjectOutputStream oos= new ObjectOutputStream(fos);
			try {
				score.save();
				// s�rialisation : �criture de l'objet dans le flux de sortie
				oos.writeObject(score); 
				// on vide le tampon
				oos.flush();
				//fermeture des flux
				oos.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

	}
	public void setBackground(String filename, boolean aEtirer) {
		terrainDeJeu.setImage(filename, aEtirer);
	}
}
