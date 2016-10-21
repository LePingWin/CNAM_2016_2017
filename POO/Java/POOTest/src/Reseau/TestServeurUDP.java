package Reseau;
import java.io.*;
import java.net.*;
/**
 * Created by pingwin on 21/10/16.
 */
public class TestServeurUDP {
    static byte buffer[] = new byte[512];
    public static void main(String argv[]) throws Exception {
        DatagramSocket socket = new DatagramSocket(12345);
        System.out.println("Lancement du Serveur");
        while(true){
            DatagramPacket paquet = new DatagramPacket(buffer,buffer.length);
            socket.receive(paquet);
            System.out.println("");
            System.out.println(paquet.getAddress());
            String donnees = new String(paquet.getData(),0,paquet.getLength());
            System.out.println("Recues = "+donnees );
            String message = "Bonjour "+donnees;
            System.out.println("Envoyees ="+message);
            DatagramPacket envoi = new DatagramPacket(message.getBytes(),
            message.length(),paquet.getAddress(),paquet.getPort());
            socket.send(envoi);
        }
    }
}
