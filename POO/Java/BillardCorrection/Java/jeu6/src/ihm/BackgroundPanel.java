package ihm;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.Jeu;

public class BackgroundPanel extends JDialog implements ActionListener {
	JLabel titre = new JLabel("Choisissez une image à afficher en fond");
	
	// Zone de texte pour le nom de fichier
	JLabel label = new JLabel("Chemin du fichier");
	JTextField filename_field = new JTextField("",20);
	JButton filename_browse = new JButton("Browse");
	
	// Checkbox pour l'étirement
	JCheckBox check = new JCheckBox("Etirement de l'image ?",true);
	
	// Bouton OK
	JButton ok = new JButton("OK");
	
	Jeu jeu = null;
	
	public BackgroundPanel(Jeu leJeu) {
		jeu = leJeu;
		
		setLayout(new BorderLayout());
		
		titre.setFont(new Font(Font.MONOSPACED,Font.BOLD, 20));
		add(titre, BorderLayout.NORTH);
		
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0; c.gridy = 0;
		panel.add(label, c);
		c.gridx = 1; c.gridy = 0;
		panel.add(filename_field, c);
		c.gridx = 2; c.gridy = 0;
		panel.add(filename_browse, c);
		
		c.gridx = 1; c.gridy = 1;
		panel.add(check, c);

		add(panel, BorderLayout.CENTER);
		
		add(ok, BorderLayout.SOUTH);
		
		filename_browse.addActionListener(this);
		ok.addActionListener(this);
		
		pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ok) {
			String filename = filename_field.getText();
			if (!filename.equals("")) 
				jeu.setBackground(filename, check.isSelected());
			setVisible(false);
		}
		else if (e.getSource() == filename_browse) {
			String filename = null;
			JFileChooser f = new JFileChooser();
			int returnVal = f.showOpenDialog(this);
	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            filename = f.getSelectedFile().getAbsolutePath();
	        }
	        filename_field.setText(filename);
		}
	}

}
