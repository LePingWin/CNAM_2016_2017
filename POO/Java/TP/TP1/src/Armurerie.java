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
        listA.add(new Arme("Blaster",5,50, Arme.Type.Direct,1));
        listA.add(new Arme("Desintegrator",100,200, Arme.Type.Explosif,3));
        listA.add(new Arme("Missiles",50,10, Arme.Type.Guide,4));
        listA.add(new Arme("Laser",2,3, Arme.Type.Direct,1));
        listA.add(new Arme("Hammer",1,8, Arme.Type.Explosif,(float)1.5));
        listA.add(new Arme("Torpille",3,3, Arme.Type.Guide,2));
        listA.add(new Arme("Mitrailleuse",2,3, Arme.Type.Direct,1));
        listA.add(new Arme("EMG",1,7, Arme.Type.Explosif,(float)1.5));
        listA.add(new Arme("Missile",4,100, Arme.Type.Guide,4));
        setListArmes(listA);
    }

}
