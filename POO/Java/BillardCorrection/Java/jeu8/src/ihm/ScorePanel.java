package ihm;

import static java.awt.BorderLayout.CENTER;
//import static org.jfree.chart.plot.PlotOrientation.VERTICAL;

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

//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.JFreeChart;
//import org.jfree.data.category.DefaultCategoryDataset;

import app.Jeu;

public class ScorePanel extends JDialog implements ActionListener {

	// Zone de texte pour la taille de la bille
	JLabel taille_label = new JLabel("Taille du rayon de la bille en pixels : ");
	int taille = 0;
	JTextField taille_field = new JTextField("",20);
	
	// Zone de texte pour la couleur
	JLabel couleur = new JLabel("      ");
	Color color = Color.yellow;
	// Bouton de sélection de la couleur
	JButton couleur_bouton = new JButton("Couleur de la bille");
	JButton add = new JButton("Ajouter cette bille");
	
	JList listeBilles = new JList();
	JButton ok = new JButton("OK");
	JButton cancel = new JButton("Reset");
	
	Jeu jeu = null;

	public ScorePanel(Jeu leJeu) {
		jeu = leJeu;
		
		setLayout(new BorderLayout());

		add(new JLabel("Les scores !!!"), BorderLayout.NORTH);

//		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//
//		for (int i = 0; i < jeu.score.getScores().length; i++) {
//			dataset.addValue(jeu.score.getScores()[i], "", new Integer(i));
//		}
//		
//		final JFreeChart barChart = ChartFactory.createBarChart(
//				"Les meilleurs scores", "Place", "Score", 
//				dataset, VERTICAL, true, true, false);
//		final ChartPanel cPanel = new ChartPanel(barChart);
//		
//		add(cPanel, BorderLayout.CENTER);
		setResizable(false);
		
		pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ok) {
			this.setVisible(false);
		}
	}

}
