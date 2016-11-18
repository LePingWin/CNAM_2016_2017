import java.util.ArrayList;

/**
 * Created by Loïc on 18/11/2016.
 */
public class SpaceInvaders {

    private ArrayList<Joueur> joueurs;

    public ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(ArrayList<Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    public SpaceInvaders(){
        init();
    }

    /**
     * Initialise 3 joueurs
     */
    private void init(){
        ArrayList<Joueur> listJ = new ArrayList<Joueur>();
        listJ.add(new Joueur("Wayne","Bruce","Batman"));
        listJ.add(new Joueur("Parker","Peter","Spiderman"));
        listJ.add(new Joueur("Victime","Jean Eugène de la Figoulette","SuperBouffon"));
        setJoueurs(listJ);
    }

    public static void main(String[] args) {
        SpaceInvaders game = new SpaceInvaders();
        for ( Joueur j : game.getJoueurs()) {
            System.out.println(j.toString());
        }
    }


}
