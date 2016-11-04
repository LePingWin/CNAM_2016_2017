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
import View.*;

public class Jeu implements ActionListener, KeyListener {
	private static JFrame frame = new JFrame("Super Jeu de billard");
	private static GridBagConstraints grille = new GridBagConstraints();
	private static JPanel panel = new JPanel(new GridBagLayout());
	private static JLabel titre_label = new JLabel("Super Jeu de billard");
	private static ZoneJeu zone = new ZoneJeu();
	private static JMenuBar menu = new JMenuBar();
	private static JMenu menu_Jeu = new JMenu("Jeu");
	private static JMenuItem menu_Jeu_Quit = new JMenuItem("Quitter", KeyEvent.VK_Q);

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	private static void createAndShowGUI() {
		Jeu jeu = new Jeu();
	}
	
	public Jeu() {
		titre_label.setFont(new Font(Font.MONOSPACED,Font.BOLD, 20));
		titre_label.setHorizontalAlignment(JLabel.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		grille.fill = GridBagConstraints.HORIZONTAL;
		grille.gridy = 0;
		panel.add(titre_label, grille);
		grille.gridy = 1;
		panel.add(zone, grille);

		createMenu();

		frame.add(panel);
		frame.pack();
		frame.setSize(new Dimension(frame.getWidth()+50, frame.getHeight()+50));
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((screen.width - frame.getWidth())/2,(screen.height - frame.getHeight())/2);
		frame.setVisible(true);
		frame.setFocusTraversalKeysEnabled(false);
		frame.addKeyListener(this);
	}
	
	private void createMenu() {
		menu_Jeu_Quit.addActionListener(this);
		menu_Jeu.add(menu_Jeu_Quit);
		menu.add(menu_Jeu);
		frame.setJMenuBar(menu);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if ( e.getSource() == menu_Jeu_Quit ) close();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if ( e.getSource() == menu_Jeu_Quit ) close();
	}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}

	private void close() {
		frame.dispose();
		System.exit(0);
	}
}
