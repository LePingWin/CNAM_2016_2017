import java.util.ArrayList;

/**
 * Created by Loïc on 18/11/2016.
 */
public class Vaisseau {
    /**
     * Maximum de points de structure
     */
    private int maxPointStructure;
    /**
     * Maximum de points de bouclier
     */
    private int maxPointBouclier;

    /**
     * Points de structure actuel
     */
    private int pointStructure = getPointStructure();
    /**
     * Points de bouclier actuel
     */
    private int pointBouclier = getMaxPointBouclier();

    /**
     * Est-t-il détruit ?
     */
    private boolean estDetruit = false;
    /**
     * Liste d'armes du vaisseau
     */
    private ArrayList<Arme> listArmes = new ArrayList<Arme>();

    /**
     * *
     * Getter/Setter
     * *
     */
    public int getMaxPointStructure() {
        return maxPointStructure;
    }

    public void setMaxPointStructure(int maxPointStructure) {
        this.maxPointStructure = maxPointStructure;
    }

    public int getMaxPointBouclier() {
        return maxPointBouclier;
    }

    public void setMaxPointBouclier(int maxPointBouclier) {
        this.maxPointBouclier = maxPointBouclier;
    }

    public boolean isEstDetruit() {
        return estDetruit;
    }

    public void setEstDetruit(boolean estDetruit) {
        this.estDetruit = estDetruit;
    }

    public ArrayList<Arme> getListArmes() {
        return listArmes;
    }

    private void setListArmes(ArrayList<Arme> listArmes) {
        this.listArmes = listArmes;
    }

    public int getPointStructure() {
        return pointStructure;
    }

    public void setPointStructure(int pointStructure) {
        this.pointStructure = pointStructure;
    }

    public int getPointBouclier() {
        return pointBouclier;
    }

    public void setPointBouclier(int pointBouclier) {
        this.pointBouclier = pointBouclier;
    }

    /**
     * Constructeur du vaisseau
     * @param maxPointStructure
     * @param maxPointBouclier
     */
    public Vaisseau(int maxPointStructure,int maxPointBouclier){
        this.maxPointStructure = maxPointStructure;
        this.maxPointBouclier = maxPointBouclier;
    }

    /**
     * Ajoute une arme uniquement si il reste de la place (<3)
     * @param arme
     */
    public void addArme(Arme arme) throws ArmurerieException {
        ArrayList<Arme> listA = getListArmes();
        if(Armurerie.getInstance().getListArmes().contains(arme)) {
            //Si la liste contient déjà 3 armes
            if (listA.size() >= 3) {
                return;
            } else {
                listA.add(arme);
            }
            setListArmes(listA);
        }else{
            throw new ArmurerieException("L'arme n'existe pas dans l'armurerie");
        }
        return;

    }

    /**
     * Enlève une arme si elle existe
     * @param arme
     */
    public void removeArme(Arme arme){
        ArrayList<Arme> listA = getListArmes();
        if(listA.contains(arme)){
            listA.remove(arme);
        }
        return;
    }

    /**
     * Affiche les armes présentes sur le vaisseau
     */
    public void printArmes(){
        for ( Arme a: getListArmes()) {
            System.out.println(a.toString());
        }
    }

    /**
     * Calcule les dégats infligés moyens
     * @return
     */
    public int puissanceDeFeuMoyenne(){
        int degats = 0;
        if(getListArmes().size() == 0)
            return 0;
        for (Arme a : getListArmes()) {
            degats += (a.getDegatsMax() + a.getDegatsMin())/2;
        }
        degats /=  getListArmes().size();
        return degats;
    }

    /**
     * Renvoi le statut du vaisseau
     */
    public String statut(){
        return "Point de Structure : " + getPointStructure() + " Point de Bouclier: " + getPointBouclier();
    }

    @Override
    public String toString() {
        return statut() + " Puissance de Feu Moyenne : " + puissanceDeFeuMoyenne();
    }
}
