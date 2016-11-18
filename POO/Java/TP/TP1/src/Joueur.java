import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

/**
 * Created by Loïc on 18/11/2016.
 */
public class Joueur {
    /**
     * *
     * Attributs
     * *
     */

    /**
     * Nom du Joueur
     */
    final String nom;
    /**
     * Prénom du Joueur
     */
    final String prenom;
    /**
     * Pseudo du Joueur
     */
    private String pseudo;

    /**
     * *
     * Getter/Setter
     * *
     */

    /**
     * Getter Nom/Prenom
     * @return
     */
    public String getNom() {
        return nom + " " + prenom;
    }

    /**
     * Getter Pseudo
     * @return
     */
    public String getPseudo() {
        return pseudo;
    }

    /***
     * Setter Pseudo
     * @param pseudo
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * Constructeur de Joueur
     * @param nom
     * @param prenom
     * @param pseudo
     */
    public Joueur(String nom, String prenom, String pseudo){
        this.nom = formatString(nom);
        this.prenom = formatString(prenom);
        this.pseudo = pseudo;
    }

    private static String formatString(String str){
        str.replaceAll("\\w*", str.substring(0, 1).toUpperCase() + str.substring(1));
        return str;
    }

    /**
     * Renvoi Pseudo ( NomPrenom )
     * @return
     */
    @Override
    public String toString() {
        return getPseudo() + "( " + getNom() + " )" ;
    }

    /**
     * Compare deux pseudos et renvoi vrai si ce sont les mêmes
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() == Joueur.class) {
            Joueur player = (Joueur)obj;
            if(player.getPseudo().equals(this.getPseudo())){
                return true;
            }
        }
        return false;
    }
}
