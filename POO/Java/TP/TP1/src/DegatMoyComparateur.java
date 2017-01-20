import java.util.Comparator;

/**
 * Created by Loïc on 20/01/2017.
 */
public class DegatMoyComparateur implements Comparator<Arme>{

    @Override
    public int compare(Arme o1, Arme o2) {
            if(o2.getAvgDamages() > o1.getAvgDamages()){
                return 1;
            }else if(o2.getAvgDamages()== o1.getAvgDamages()){
                return 0;
            }
            return -1;
        }
}
