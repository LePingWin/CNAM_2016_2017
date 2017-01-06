import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Loïc on 18/11/2016.
 */
public class SpaceInvaders {

    /***
     * Liste des Joueurs
     */
    private static ArrayList<Joueur> joueurs;

    /***
     * Liste des Vaisseaux
     */
    private ArrayList<Vaisseau> ennemis;

    public static ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }

    public static void setJoueurs(ArrayList<Joueur> joueur) {
        joueurs = joueur;
    }

    public ArrayList<Vaisseau> getEnnemis() {
        return ennemis;
    }

    public void setEnnemis(ArrayList<Vaisseau> ennemis) {
        this.ennemis = ennemis;
    }

    private Armurerie getArmurerie() {
        return Armurerie.getInstance();
    }

    public SpaceInvaders() throws ArmurerieException{
        init();
    }

    /**
     * Initialise 3 joueurs
     */
    private void init() throws ArmurerieException{
        populateJoueurs();
        populateEnnemis();
    }

    /**
     * Rempli la liste de Joueurs
     * @throws ArmurerieException
     */
    private static void populateJoueurs() throws ArmurerieException{
        ArrayList<Joueur> listJ = new ArrayList<Joueur>();
        listJ.add(new Joueur("Wayne","bruce","Batman"));
        listJ.add(new Joueur("Parker","Peter","Spiderman"));
        listJ.add(new Joueur("Victime","Jean-eugène de la figoulette","SuperBouffon",new ViperMKII()));
        setJoueurs(listJ);
    }

    /**
     * Rempli la liste d'ennemis
     * @throws ArmurerieException
     */
    private void populateEnnemis()  throws ArmurerieException{
        ArrayList<Vaisseau> listE = new ArrayList<Vaisseau>();
        listE.add(new Slavel());
        listE.add(new Alkesh());
        listE.add(new Assault());
        listE.add(new Dart());
        listE.add(new Tardis());
        setEnnemis(listE);
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

    /***
     * Gère les tours
     */
    public void nextTurn(){
        Vaisseau joueurVaisseau = getJoueurs().get(0).getVaisseau();
        ArrayList<Vaisseau> ennemis = new ArrayList<Vaisseau>();

        //Tri les ennemis en vie
        for (Vaisseau e: getEnnemis()){
            if(!e.isEstDetruit()){
                ennemis.add(e);
            }
        }
        //Recharge celui des vaisseaux
        if(joueurVaisseau.getPointBouclier() < joueurVaisseau.getMaxPointBouclier()){
            joueurVaisseau.setPointBouclier(joueurVaisseau.getPointBouclier()+2 > joueurVaisseau.getMaxPointBouclier() ? joueurVaisseau.getMaxPointBouclier() : joueurVaisseau.getPointBouclier()+2);
            System.out.println("Rechargement du bouclier de "+joueurVaisseau.getClass().getName()+", il a maintenant "+joueurVaisseau.getPointBouclier()+" points de bouclier");
        }

        for (Vaisseau e: ennemis
             ) {
            //Recharge le bouclier des ennemis
            if( e.getPointBouclier() < e.getMaxPointBouclier()){
                e.setPointBouclier(e.getPointBouclier()+2 > e.getMaxPointBouclier() ? e.getMaxPointBouclier() : e.getPointBouclier()+2);
                System.out.println("Rechargement du bouclier de "+e.getClass().getName()+", il a maintenant "+e.getPointBouclier()+" points de bouclier");
            }
            //Utilise les aptitudes
            if(e instanceof  IAptitude){
                ((IAptitude) e).utilise(ennemis);
            }

        }



        //Gère les tirs
        Random r = new Random();
        int posTirJoueur = r.nextInt(ennemis.size());
        int posTirEnnemis = 0;
        for (Vaisseau ennemiVaisseau: ennemis
             ) {
            if(posTirEnnemis == posTirJoueur){
                if(joueurVaisseau.isEstDetruit()){
                    return;

                }
                joueurVaisseau.attaque(ennemis.get(posTirJoueur));
            }
            if(ennemiVaisseau.isEstDetruit()){
                break;
            }
            ennemiVaisseau.attaque(joueurVaisseau);
            posTirEnnemis++;
        }

    }

    /**
     * Indique si la partie est terminée
     * @return vrai si la partie continue
     */
    public boolean gameOver(){
        Joueur joueur = getJoueurs().get(0);
        Vaisseau joueurVaisseau = joueur.getVaisseau();
        if(joueurVaisseau.isEstDetruit()){
            return false;
        }
        //Si ennemis en vie on continue
        boolean anyAlive = false;
        for (Vaisseau e: getEnnemis()
             ) {
            if (!e.isEstDetruit()){
                anyAlive = true;
            }
        }
        return anyAlive;

        }

    public static void main(String[] args) throws ArmurerieException, InterruptedException {
        SpaceInvaders game = new SpaceInvaders();
        int nbToursTotal = 0;
        //Routine de jeu
        while (game.gameOver()){
            nbToursTotal++;
            System.out.println("Tour n°"+nbToursTotal);
            game.nextTurn();
            Thread.sleep(2000);

        }

    }



}
