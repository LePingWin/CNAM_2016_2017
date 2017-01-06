/**
 * Created by Loïc on 06/01/2017.
 */
public class Alkesh extends Vaisseau {
    public Alkesh()throws ArmurerieException {
        super(3, 5);
        this.addArme(new Arme("Torpille",3,3, Arme.Type.Guide,2));
    }

    @Override
    public void addArme(Arme arme) throws ArmurerieException {
        super.addArme(arme);
        if (arme.getType() == Arme.Type.Guide) {
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