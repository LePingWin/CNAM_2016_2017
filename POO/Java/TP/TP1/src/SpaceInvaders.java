import java.io.IOException;
import java.util.*;

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

    /**
     * Menus a afficher
     */
    private static ArrayList<String> navMenu =  new ArrayList<String>(Arrays.asList("** Menu de Navigation **","1.Gestion des joueurs","2.Gestion du Vaisseau","3.Gestion de l'armurerie","4.Lancer Partie1z","5.Quitter"));
    private static ArrayList<String> playerMenu =  new ArrayList<String>(Arrays.asList("** Menu de Gestion du Joueur **","1.Créer un joueur","2.Supprimer un Joueur","3.Choisir un Joueur","4.Retourner au Menu Principal"));
    private static ArrayList<String> shipMenu =  new ArrayList<String>(Arrays.asList("** Menu de Gestion du Vaisseau **","1.Lister les Vaisseaux","2.Retourner au Menu Principal"));
    private static ArrayList<String> armurerieMenu =  new ArrayList<String>(Arrays.asList("** Menu de l'Armurerie **","1.Lister les armes","2.Lister les 5 armes avec les dommages moyens les plus hauts","3.Lister les 5 armes avec les dommages minimums les plus hauts","4.Retourner au Menu Principal"));

    public static ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }

    public static void setJoueurs(ArrayList<Joueur> joueur) {
        joueurs = joueur;
    }

    public static ArrayList<String> getPlayerMenu() {
        return playerMenu;
    }

    public static ArrayList<String> getShipMenu() {
        return shipMenu;
    }

    public static ArrayList<String> getArmurerieMenu() {
        return armurerieMenu;
    }

    public ArrayList<Vaisseau> getEnnemis() {
        return ennemis;
    }

    public void setEnnemis(ArrayList<Vaisseau> ennemis) {
        this.ennemis = ennemis;
    }

    public static ArrayList<String> getNavMenu() {
        return navMenu;
    }

    private Armurerie getArmurerie() {
        return Armurerie.getInstance();
    }

    public SpaceInvaders(String path) throws ArmurerieException, IOException {
        init();
        if(path != "") {
            ArmeImporteur ai = new ArmeImporteur(path, new ArrayList<>(), 16);
            ai.importWeapons();
        }
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
    private void populateEnnemis() throws ArmurerieException {
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

    /**
     * Affiche le menu de navigation
     */
    public static void showNavMenu() throws IOException {
        getNavMenu().forEach( (item) -> System.out.println(item));
        Scanner in = new Scanner(System.in);
        switch (in.nextInt()){
            case 1:
                showPlayerMenu();
                break;
            case 2:
                showShipMenu();
                break;
            case 3:
                showArmurerieMenu();
                break;
            case 4:

                break;
            default:
                System.exit(0);
                break;
        }

    }

    /**
     * Affiche le menu de gestion du joueur
     */
    public static void showPlayerMenu() throws IOException {
        getPlayerMenu().forEach( (item) -> System.out.println(item));
        Scanner in = new Scanner(System.in);
        switch (in.nextInt()){
            case 1:
                String nom;
                String prenom;
                String pseudo;
                in.nextLine();
                System.out.println("Entrez un nom");
                nom = in.nextLine();
                System.out.println("Entrez un prénom");
                prenom = in.nextLine();
                System.out.println("Entrez un pseudo");
                pseudo = in.nextLine();
                getJoueurs().add(new Joueur(nom,prenom,pseudo));
                break;
            case 2:
                int i = 0;
                for (Joueur j : getJoueurs()){
                    System.out.println(i++ +". "+ j.toString());
                }
                int choix = in.nextInt();
                if(choix < i ){
                    getJoueurs().remove(choix);
                }else{
                    System.out.println("Aucun joueur n'a été supprimé car l'index selectionné n'existe pas !");
                }
                break;
            case 3:
                int i2 = 0;
                for (Joueur j : getJoueurs()){
                    System.out.println(i2++ +". "+ j.toString());
                }
                int choix2 = in.nextInt();
                if(choix2 < i2 ){
                    Joueur j = getJoueurs().get(choix2);
                    getJoueurs().remove(j);
                    getJoueurs().add(j);
                    Collections.reverse(getJoueurs());
                }else{
                    System.out.println("Aucun joueur n'a été sélectionner car l'index selectionné n'existe pas !");
                }
                break;
            default:
                break;
        }

        showNavMenu();
    }

    /**
     * Affiche le menu du vaisseau
     */
    public static void showShipMenu() throws IOException {
        getShipMenu().forEach( (item) -> System.out.println(item));
        Scanner in = new Scanner(System.in);
        switch (in.nextInt()){
            case 1:
                System.out.println("Vaisseaux des joueurs");
                for ( Joueur j: getJoueurs()
                     ) {
                    System.out.println(j.getPseudo() + " - " + j.getVaisseau().toString());
                }
                break;
            default:
                break;
        }
        showNavMenu();
    }

    /**
     * Affiche le menu de l'armurerie
     */
    public static void showArmurerieMenu() throws IOException {
        getArmurerieMenu().forEach( (item) -> System.out.println(item));
        Scanner in = new Scanner(System.in);
        Armurerie a = Armurerie.getInstance();
        ArrayList<Arme> armes = a.getListArmes();
        int i = 0;
        switch (in.nextInt()){
            case 1:
                a.getListArmes().forEach( (arme) -> System.out.println(arme.toString()));
                break;
            case 2:

                Collections.sort(armes,new DegatMoyComparateur());

                for (Arme ar: armes
                     ) {
                    if(i<5){
                        System.out.println(ar.toString());
                        i++;
                    }else{
                        break;
                    }
                }
                break;
            case 3:
                Collections.sort(armes,new DegatMinComparateur());
                for (Arme ar: armes
                        ) {
                    if(i<5){
                        System.out.println(ar.toString());
                        i++;
                    }else{
                        break;
                    }
                }
                break;
            default:
                break;
        }
        showNavMenu();

    }
    public static void main(String[] args) throws ArmurerieException, InterruptedException, IOException {
        SpaceInvaders game;
        if(args.length != 0) {
           game = new SpaceInvaders(args[0]);
        }else{
            game = new SpaceInvaders("");
        }

        showNavMenu();

        int nbToursTotal = 0;
        //Routine de jeu
        while (game.gameOver()) {
            nbToursTotal++;
            System.out.println("Tour n°" + nbToursTotal);
            game.nextTurn();
            Thread.sleep(2000);

        }
    }



}
