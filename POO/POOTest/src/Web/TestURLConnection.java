package Web;
import java.io.*;
import java.net.*;
/**
 * Created by pingwin on 21/10/16.
 */
public class TestURLConnection {
    public static void main(String [] args){
        String donnees;
        try{
            URL monURL = new URL("file:///home/pingwin/Documents/CNAM_2016_2017/test.html");
            URLConnection connexion = monURL.openConnection();
            InputStream flux = connexion.getInputStream();
            int donneesALire = connexion.getContentLength();
            for(;donneesALire != 0;donneesALire--)
                System.out.print((char)flux.read());
            flux.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
