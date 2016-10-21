/**
 * Created by pingwin on 21/10/16.
 */
import javax.swing.*;
public class HelloWordSwing {
    private static void createAndShowGUI(){
        //Création de la fenêtre
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel(("Hello World"));
        frame.getContentPane().add(label);

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
