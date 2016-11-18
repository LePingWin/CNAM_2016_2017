import java.util.ArrayList;

/**
 * Created by Loïc on 18/11/2016.
 */
public class Armurerie {
    private ArrayList<Arme> listArmes;
    private static Armurerie instance = null;

    public ArrayList<Arme> getListArmes() {
        return listArmes;
    }

    public void setListArmes(ArrayList<Arme> listArmes) {
        this.listArmes = listArmes;
    }

    public static Armurerie getInstance() {
        if(instance == null){
            instance = new Armurerie();
        }
        return instance;
    }

    private Armurerie(){
        init();
    }

    /**
     * Initialise 3 armes des 3 types possibles
     */
    private void init() {
        ArrayList<Arme> listA = new ArrayList<Arme>();
        listA.add(new Arme("Blaster",5,50, Arme.Type.Direct));
        listA.add(new Arme("Desintegrator",100,200, Arme.Type.Explosif));
        listA.add(new Arme("Missiles",50,10, Arme.Type.Guide));
        setListArmes(listA);
    }

}
