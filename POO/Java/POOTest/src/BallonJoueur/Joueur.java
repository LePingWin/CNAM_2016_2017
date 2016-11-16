package BallonJoueur;

/**
 * Created by pingwin on 21/10/16.
 */
public class Joueur extends Thread {
    public Ballon monBallon;
    public String nom;
    public Joueur(Ballon b, String n) {monBallon = b; nom = n;}
    public void run(){for(;;){
        monBallon.prendreDonner(this);
        try{
            Joueur.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }}
}
