import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * Vaisseau du Joueur
     */
    private Vaisseau vaisseau;

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

    public Vaisseau getVaisseau() {
        return vaisseau;
    }

    public void setVaisseau(Vaisseau vaisseau) {
        this.vaisseau = vaisseau;
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
        this.vaisseau = new Vaisseau(100,100);
    }

    /**
     * Constructeur de joueur avec vaisseau
     * @param nom
     * @param prenom
     * @param pseudo
     * @param vaisseau
     */
    public Joueur(String nom, String prenom, String pseudo,Vaisseau vaisseau){
        this.nom = formatString(nom);
        this.prenom = formatString(prenom);
        this.pseudo = pseudo;
        this.vaisseau = vaisseau;
        }

    /**
     * Met la première lettre en Majuscule et le reste en minuscule
     * @param str
     * @return
     */
    private static String formatString(String str){
        Pattern p = Pattern.compile("[\\wéèàù]+"); //
        Matcher m = p.matcher(str);
        String str2 ="";
        int i =0;
        while(m.find()){
            if(m.group(i) != " ")
                str2 += m.group(i).substring(0, 1).toUpperCase() + m.group(i).substring(1).toLowerCase();
            if(i < m.groupCount())
            i++;
        }
        return str2;
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
