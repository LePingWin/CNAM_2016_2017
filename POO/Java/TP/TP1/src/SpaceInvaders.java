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

    private Armurerie getArmurerie() {
        return Armurerie.getInstance();
    }

    public SpaceInvaders(){
        init();
    }

    /**
     * Initialise 3 joueurs
     */
    private void init(){
        ArrayList<Joueur> listJ = new ArrayList<Joueur>();
        listJ.add(new Joueur("Wayne","bruce","Batman"));
        listJ.add(new Joueur("Parker","Peter","Spiderman"));
        listJ.add(new Joueur("Victime","Jean-eugène de la figoulette","SuperBouffon",new Vaisseau(5,2)));
        setJoueurs(listJ);

    }

    public void afficheArmurerieInfo(){
        for (Arme a: getArmurerie().getListArmes()
                ) { System.out.println(a.toString());
        }
    }

    public void afficheVaisseauInfo(Joueur j){
        System.out.println(j.getVaisseau().toString());
    }

    public void afficheJoueursInfo(){
        for ( Joueur j : this.getJoueurs()) {
            System.out.println(j.toString());
        }
    }

    public static void main(String[] args) {
        SpaceInvaders game = new SpaceInvaders();
        System.out.println("Joueurs");
        game.afficheJoueursInfo();
        System.out.println("Armurerie");
        game.afficheArmurerieInfo();
        System.out.println("Vaisseaux");
        for (Joueur j: game.getJoueurs()
             ) {
            game.afficheVaisseauInfo(j);

        }

    }



}
