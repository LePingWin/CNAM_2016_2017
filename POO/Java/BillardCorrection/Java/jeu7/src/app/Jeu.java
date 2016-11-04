package app;

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

import donnee.Terrain;
import donnee.Bille;
import ia.ZoneJeu;
import ihm.*;

public class Jeu implements ActionListener, KeyListener {
// TODO dispose + save aux bons endroits

	private Thread animThread = null;
	// TODO Ajouter browse pour le fichier de fond + checkbox
	// Création de la fenêtre
	private JFrame frame = new JFrame("Super Jeu de billes");
	// Création d'un modèle d'organisation de l'affichage
	private GridBagConstraints grille = new GridBagConstraints();
	// Création d'un panel pour contenir l'ensemble des objets
	private JPanel panel = new JPanel(new GridBagLayout());
	// Création d'un texte pour le titre
	private JLabel titre_label = new JLabel("Super Jeu de billes");
	// Création d'une zone de jeu
	private Terrain terrainDeJeu = new Terrain();
	private ZoneJeu zone = new ZoneJeu(this, terrainDeJeu);
	// Création de la barre de menus et les menus
	private JMenuBar menu = new JMenuBar();
	private JMenu menu_Animation = new JMenu("Animation");
	private JMenu menu_Jeu = new JMenu("Jeu");
	private JMenuItem menu_Jeu_New = new JMenuItem("Nouvelle Bille");
	private JMenuItem menu_Jeu_Score = new JMenuItem("High Score");
	private JMenuItem menu_Jeu_Back = new JMenuItem("Image de fond");
	private JMenuItem menu_Jeu_Quit = new JMenuItem("Quitter", KeyEvent.VK_Q);
	private JMenuItem menu_Animation_Start = new JMenuItem("Démarrer", KeyEvent.VK_D);
	private JMenuItem menu_Animation_Stop = new JMenuItem("Arrêter", KeyEvent.VK_A);
	private JMenu menu_Aide = new JMenu("Aide");

	public static Score score = new Score();
	
	public static void main(String[] args) {
		// Lance la création d'une fenêtre via un thread
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	private static void createAndShowGUI() {
		Jeu jeu = new Jeu();
		
	}
	private void loadScore() {
		// recharge les scores
		try {	
			FileInputStream fos = new FileInputStream("scores.serial");
			ObjectInputStream ois= new ObjectInputStream(fos);
			// désérialisation : lecture de l'objet depuis le flux d'entrée
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
		
		// Définit son action sur un clic sur le bouton de fermeture (la croix "X")
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Affecte les contraintes d'affichage à la grille
		// remplissage horizontal
		grille.fill = GridBagConstraints.HORIZONTAL;
		// position sur la 1ere ligne 
		grille.gridy = 0;
		// Ajoute le titre dans le panel avec les contraintes de positions définies précédemment
		panel.add(titre_label, grille);
		// Ajoute la zone de jeu sur la 2e ligne
		grille.gridy = 1;
		panel.add(zone, grille);

		createMenu();

		// Ajoute le panel dans la fenetre
		frame.add(panel);
		
		// Prépare la fenêtre à s'afficher
		frame.pack();
		// Agrandit un peu la fenetre
		frame.setSize(new Dimension(frame.getWidth()+50, frame.getHeight()+50));
		// On récupère la taille de l'écran (la résolution)
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		// et on place notre fenêtre au milieu
		frame.setLocation((screen.width - frame.getWidth())/2,(screen.height - frame.getHeight())/2);

		// Affiche la fenêtre à l'écran
		frame.setVisible(true);
		frame.setFocusTraversalKeysEnabled(false);
		frame.addKeyListener(this);

		loadScore();		
	}
	
	private void createTitle() {
		// Configure le texte du titre en gras et taille 20
		titre_label.setFont(new Font(Font.MONOSPACED,Font.BOLD, 20));
		// Le centre Horizontalement
		titre_label.setHorizontalAlignment(JLabel.CENTER);
	}
	/**
	 *  Création et remplissage du menu
	 */
	private void createMenu() {
		menu_Jeu_New.addActionListener(this);
		menu_Jeu_Score.addActionListener(this);
		menu_Jeu_Back.addActionListener(this);
		menu_Jeu_Quit.addActionListener(this);
		menu_Animation_Start.addActionListener(this);
		menu_Animation_Stop.addActionListener(this);
		menu_Jeu.add(menu_Jeu_New);
		menu_Jeu.add(menu_Jeu_Score);
		menu_Jeu.add(menu_Jeu_Back);
		menu_Jeu.add(menu_Jeu_Quit);
		menu_Animation.add(menu_Animation_Start);
		menu_Animation.add(menu_Animation_Stop);
		menu.add(menu_Jeu);
		menu.add(menu_Animation);
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
		else if (e.getSource() == menu_Jeu_Back) {
			image.setVisible(true);
			image.setLocationRelativeTo(panel);
		}
		else if ( e.getSource() == menu_Jeu_Quit )
			close();
		else if ( e.getSource() == menu_Animation_Start )
			start();
		else if ( e.getSource() == menu_Animation_Stop )
			stop();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			close();
		else if (e.getKeyCode() == KeyEvent.VK_D)
			start();
		else if (e.getKeyCode() == KeyEvent.VK_A)
			stop();
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

	public void start() {
		if (animThread == null) {
			animThread = new Thread(zone, "ThreadDeLaZoneDeJeu");
			animThread.start();
		}
	}

	public void stop() {
		if (animThread != null) {
			animThread.stop();
			animThread = null;
		}
	}

	public Thread getAnimThread() {
		return animThread;
	}
	public void setAnimThread(Thread animThread) {
		this.animThread = animThread;
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
			// création d'un "flux objet" avec le flux fichier
			ObjectOutputStream oos= new ObjectOutputStream(fos);
			try {
				score.save();
				// sérialisation : écriture de l'objet dans le flux de sortie
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
		terrainDeJeu.repaint();
	}
}
