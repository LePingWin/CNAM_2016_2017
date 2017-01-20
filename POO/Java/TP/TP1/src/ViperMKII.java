import java.util.ArrayList;

/**
 * Created by Loïc on 06/01/2017.
 */
public class ViperMKII extends Vaisseau {

    public ViperMKII() {
        super(10, 15);
        try {
            this.addArme(new Arme("Mitrailleuse",2,3, Arme.Type.Direct,1));
            this.addArme(new Arme("EMG",1,7, Arme.Type.Explosif,(float)1.5));
            this.addArme(new Arme("Missile",4,100, Arme.Type.Guide,4));
        }catch (ArmurerieException e){
            e.printStackTrace();
        }
    }

    @Override
    public void attaque(Vaisseau v) {
        ArrayList<Arme> canShoot = new ArrayList<>();
        for (Arme a: getListArmes()
             ) {
                if( a.getNbTours()-1 == 0){
                    canShoot.add(a);
                }
        };
        Arme maxDamage = canShoot.get(0);
        for (Arme a: canShoot
                ) {
                if(a.getDegatsMax() > maxDamage.getDegatsMax() ){
                    maxDamage = a;
                }
        };
        int deg = maxDamage.tirer();
        System.out.println(this.getClass().getName()+" de " + SpaceInvaders.getJoueurs().get(0).getPseudo() +" inflige "+deg+" points de dégâts à "+v.getClass().getName());
        v.takeDamage(deg);
        canShoot.remove(maxDamage);
        canShoot.forEach(a -> a.setNbTours(a.getNbTours()-1));
    }

}
