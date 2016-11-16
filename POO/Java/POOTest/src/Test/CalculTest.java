package Test;

import org.junit.Assert;
import org.junit.Test;
/**
 * Created by pingwin on 21/10/16.
 */
public class CalculTest{

    @Test
    public void add() throws Exception {
        Assert.assertEquals("2+2=4",4,Calcul.add(2,2));
        Assert.assertEquals("5+3=8",8,Calcul.add(5,3));
        Assert.assertEquals("0+7=7",7,Calcul.add(0,7));
    }

}