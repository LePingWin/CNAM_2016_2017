/**
 * Created by Loïc on 18/11/2016.
 */
public class Arme {
    private String nom;
    private int degatsMin;
    private int degatsMax;
    public enum Type {Direct,Explosif,Guide};
    private Type type;

    public Arme(String nom, int degatsMin, int degatsMax, Type type){
        this.nom=nom;
        this.degatsMin = degatsMin;
        this.degatsMax = degatsMax;
        this.type = type;
    }
}
