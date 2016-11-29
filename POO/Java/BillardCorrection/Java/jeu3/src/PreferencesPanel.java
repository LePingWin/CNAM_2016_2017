import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PreferencesPanel extends JDialog implements ActionListener {

	// Zone de texte pour la taille de la bille
	JLabel taille_label = new JLabel("Taille du rayon de la boule en pixels : ");
	int taille = 0;
	JTextField taille_field = new JTextField("",20);
	
	// Zone de texte pour la couleur
	JLabel couleur = new JLabel("      ");
	Color color = Color.yellow;
	// Bouton de sélection de la couleur
	JButton couleur_bouton = new JButton("Couleur de la boule");
	JButton add = new JButton("Ajouter cette boule");
	
	JList listeBilles = new JList();
	JButton ok = new JButton("OK");
	JButton cancel = new JButton("Reset");
	
	Jeu jeu = null;

	public PreferencesPanel(Jeu leJeu) {
		jeu = leJeu;
		
		setLayout(new BorderLayout());

		add(new JLabel("CREATION D'UNE NOUVELLE BOULE"), BorderLayout.NORTH);
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.setPreferredSize(new Dimension(200,200));

		panel.add(taille_label);
		taille_field.addActionListener(this);
		panel.add(taille_field);

		couleur_bouton.addActionListener(this);
		couleur.setBackground(color);
		couleur.setOpaque(true);
		couleur.setBorder(BorderFactory.createLineBorder(Color.black));

		panel.add(couleur_bouton);
		panel.add(couleur);
		
		add(panel, BorderLayout.CENTER);

		// Boutons Validation / Annulation
		JPanel panelFin = new JPanel(new FlowLayout());
		ok.addActionListener(this);
		cancel.addActionListener(this);
		panelFin.add(ok);
		panelFin.add(cancel);
		add(panelFin, BorderLayout.SOUTH);
		
		// interdit de changer la taille de cette fenetre
		setResizable(false);
		
		pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == couleur_bouton) {
			Color newColor = JColorChooser.showDialog(
                    PreferencesPanel.this,
                    "Choose Background Color", color);
			couleur.setBackground(newColor);
			color = newColor;
		}
		else if (e.getSource() == ok) {
			try {
				taille = Integer.parseInt(taille_field.getText());
			} catch (NumberFormatException ex) {
				taille_field.setText(String.valueOf(taille));
				System.err.println(e);
			}
			jeu.ajouteBille(color,taille);
			this.setVisible(false);
		}
		else if (e.getSource() == cancel) {
			color = Color.YELLOW;
			couleur.setBackground(color);
			taille=0;
			taille_field.setText("");
			this.setVisible(false);
		}
	}

}
