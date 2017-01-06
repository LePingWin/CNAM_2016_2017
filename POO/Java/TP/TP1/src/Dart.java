import java.util.ArrayList;

/**
 * Created by Loïc on 06/01/2017.
 */
public class Dart extends Vaisseau {

    public Dart()throws ArmurerieException {
        super(10, 3);
        this.addArme(new Arme("Laser",2,3, Arme.Type.Direct,1));
    }

    @Override
    public void addArme(Arme arme) throws ArmurerieException {
        super.addArme(arme);
        if (arme.getType() == Arme.Type.Direct) {
            int index = this.getListArmes().indexOf(arme);
            getListArmes().get(index).setTempsDeRechargement(1);
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
