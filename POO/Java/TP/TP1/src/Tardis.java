import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Loïc on 06/01/2017.
 */
public class Tardis extends Vaisseau implements IAptitude {

    public Tardis() {
        super(1, 0);
    }

    @Override
    public void utilise(ArrayList<Vaisseau> vaisseaus) {
        int size = vaisseaus.size();
        Random r = new Random();
        int initPos = r.nextInt(size);
        int finalPos = r.nextInt(size);
        Vaisseau v1 = vaisseaus.get(initPos);
        Vaisseau v2 = vaisseaus.get(finalPos);
        System.out.println(this.getClass().getName() +" téléporte "+v1.getClass().getName()+ " à la place de "+v2.getClass().getName());
        vaisseaus.set(initPos,v2);
        vaisseaus.set(finalPos,v1);
    }

    @Override
    public void attaque(Vaisseau v) {
        int degats = 0;
        for (Arme a: getListArmes()
                ) {
            degats += a.tirer();
        }
        System.out.println(this.getClass().getName() +" inflige "+degats+" points de dégâts à "+SpaceInvaders.getJoueurs().get(0).getPseudo());
        v.takeDamage(degats);
    }
}
