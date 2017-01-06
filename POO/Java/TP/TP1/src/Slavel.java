/**
 * Created by Loïc on 06/01/2017.
 */
public class Slavel extends Vaisseau {
    public Slavel()throws ArmurerieException {
        super(30, 0);
        this.addArme(new Arme("Hammer",1,8, Arme.Type.Explosif,(float)1.5));
    }

    @Override
    public void addArme(Arme arme) throws ArmurerieException {
        super.addArme(arme);
        if (arme.getType() == Arme.Type.Explosif) {
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
