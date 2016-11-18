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
     */
    public Arme(String nom, int degatsMin, int degatsMax, Type type){
        this.nom=nom;
        this.degatsMin = degatsMin;
        this.degatsMax = degatsMax;
        this.type = type;
    }

    @Override
    public String toString() {
        return getNom() + " Type : " + getType() +  " Dégats Max : " + getDegatsMax() + " Dégats Min : " + getDegatsMin();
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
}
