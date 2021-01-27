package tp2.socket.ex1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {

	final static String HOST = "localhost";
	final static int port = 7878;
	final static int taille = 1024;
	final static byte buffer[] = new byte[taille];

	public static void main(String argv[]) throws Exception {
		// Création d'une socket client
		DatagramSocket clientSocket = new DatagramSocket();
		DatagramPacket dataRecieved = new DatagramPacket(buffer, taille);
		
		// Récuperer @IP du serveur
		InetAddress serveur = InetAddress.getByName(HOST);
		
		// Message à envoyer
		String message = "Bonjour Mr le serveur";
		// Construction du datagramme
		DatagramPacket dataSent = new DatagramPacket(message.getBytes(), message.length(), serveur, port);
		clientSocket.send(dataSent);
		
		
		// recupérer la requete à partir du socket client 
		clientSocket.receive(dataRecieved);
		
		System.out.println("Message du serveur : " + new String(dataRecieved.getData()));
		System.out.println("From : " + dataRecieved.getAddress() + ":" + dataRecieved.getPort());
		
		clientSocket.close();
	}
}
