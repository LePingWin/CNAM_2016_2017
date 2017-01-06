import java.util.ArrayList;

/**
 * Created by Loïc on 06/01/2017.
 */
public class Assault extends Vaisseau implements IAptitude {

    public Assault() {
        super(15, 0);
    }

    @Override
    public void utilise(ArrayList<Vaisseau> vaisseaus) {
        if(vaisseaus.get(0).equals(this)){
            System.out.println("Assault se suicide 10 points de dégâts sont infligés à "+SpaceInvaders.getJoueurs().get(0).getPseudo()+" !");
            SpaceInvaders.getJoueurs().get(0).getVaisseau().takeDamage(10);
            setEstDetruit(true);
        }

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
