package InterfaceHM; /**
 * Created by pingwin on 21/10/16.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelloWordSwing {
    private static void createAndShowGUI(){
        //Création de la fenêtre
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridLayout gl = new GridLayout(0,2);
        frame.setLayout(gl);

        JButton butt = new JButton("Clique moi !");
        butt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                javax.swing.JOptionPane.showMessageDialog(null,"Youpi !");
            }
        });

        frame.add(butt);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String [] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
