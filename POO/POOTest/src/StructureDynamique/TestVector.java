package StructureDynamique; /**
 * Created by pingwin on 21/10/16.
 */
import java.util.*;
public class TestVector {
    public static void main(String[] args){
        StringTokenizer lstMots = new StringTokenizer(args[0], ",.");
        Vector vecteur = new Vector();
        while(lstMots.hasMoreTokens()) {
            String mot = lstMots.nextToken();
            boolean dejaVu = false;
            for(int i = 0; i < vecteur.size();i++) {
                String motMemorise = (String)vecteur.elementAt(i);
                dejaVu = motMemorise.equals(mot);
                if(dejaVu) break;
            }
            if(!dejaVu) vecteur.add(mot);
        }
        System.out.println("==> Dans la phrase "+args[0]+"=== il y a "+
        vecteur.size() + " mots différents qui sont:");
        for (int i = 0;i<vecteur.size();i++){
            System.out.println("===>"+i+" -> "+vecteur.elementAt(i));
        }
        }
    }
