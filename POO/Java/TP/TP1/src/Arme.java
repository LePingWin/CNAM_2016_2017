import java.util.Random;

/**
 * Created by Loïc on 18/11/2016.
 */
public class Arme {
    /**
     * Nom de l'arme
     */
    private String nom;
    /**
     * Dégâts Minimum
     */
    private int degatsMin;
    /**
     * Dégâts Maximum
     */
    private int degatsMax;

    /**
     * Type de l'arme
     */
    private Type type;

    /**
     * Temps de rechargement en nb de tours
     */
    private float tempsDeRechargement;


    /**
     * Nombre de tours
     */
    private int nbTours;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getDegatsMin() {
        return degatsMin;
    }

    public void setDegatsMin(int degatsMin) {
        this.degatsMin = degatsMin;
    }

    public int getDegatsMax() {
        return degatsMax;
    }

    public void setDegatsMax(int degatsMax) {
        this.degatsMax = degatsMax;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public float getTempsDeRechargement() {
        return tempsDeRechargement;
    }

    public void setTempsDeRechargement(float tempsDeRechargement) {
        this.tempsDeRechargement = tempsDeRechargement;
    }

    public int getNbTours() {
        return nbTours;
    }

    public void setNbTours(float nbTours){
        this.nbTours = Math.round(nbTours);
    }

    /**
     * Types d'armes possibles
     */
    public enum Type {Direct,Explosif,Guide};


    /**
     * Constructeur de la classe Arme
     * @param nom
     * @param degatsMin
     * @param degatsMax
     * @param type
     * @param tempsDeRechargement
     */
    public Arme(String nom, int degatsMin, int degatsMax, Type type, float tempsDeRechargement){
        this.nom=nom;
        this.degatsMin = degatsMin;
        this.degatsMax = degatsMax;
        this.type = type;
        this.tempsDeRechargement = tempsDeRechargement;
        this.nbTours = Math.round(getTempsDeRechargement());
    }

    @Override
    public String toString() {
        return getNom() + " Type : " + getType() +  " Dégats Max : " + getDegatsMax() + " Dégats Min : " + getDegatsMin() + " Temps de rechargement : "+getTempsDeRechargement();
    }

    /**
     * Vérifie que deux armes sont les mêmes
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if(obj.getClass().equals(Arme.class)){
            Arme arme = (Arme)obj;
            if(this.getType() == arme.getType()
                    && this.getClass() == arme.getClass()
                    && this.getDegatsMax() == arme.getDegatsMax()
                    && this.getDegatsMin() == arme.getDegatsMin()){
                return  true;
            }
        }
        return false;
    }

    /**
     * Determine de le nombres de dégâts infligés
     * @return
     */
    public int tirer(){
        setNbTours(getNbTours()-1);
        int tours = getNbTours();
        if(tours != 0){
            return 0;
        }else{
            Random r = new Random();
            int degats = getDegatsMin() + r.nextInt(getDegatsMax()- getDegatsMin()+1);
            this.setNbTours(getTempsDeRechargement());
            Type typeArme = getType();
            switch (typeArme){
                case Direct:
                    int chance = r.nextInt(10+1);
                    return chance == 1 ? degats : 0;
                case Explosif:
                    chance = r.nextInt(4+1);
                    this.setNbTours(getTempsDeRechargement()*2);
                    return chance == 1 ? degats*2 : 0;
                case Guide:
                    return getDegatsMin();
            }
            return degats;
        }
    }

    /**
     * Renvoi les dégâts moyens de l'arme
     * @return
     */
    public int getAvgDamages(){
        return (this.getDegatsMax() + this.getDegatsMin())/2;
    }
}
