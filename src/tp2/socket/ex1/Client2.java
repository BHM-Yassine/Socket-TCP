package tp2.socket.ex1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client2 {

	final static int port = 7878;
	final static int taille = 1024;
	final static String HOST = "localhost";
	final static byte buffer[] = new byte[taille];

	public static void main(String argv[]) throws Exception {
		// Cr�ation d'une socket client
		DatagramSocket clientSocket = new DatagramSocket();
		
		// R�cuperer @IP du serveur
		InetAddress serveur = InetAddress.getByName(HOST);
		
		// Message � envoyer
		String message = "Bonjour Mr le serveur 22222";
		// Construction du datagramme
		DatagramPacket dataSent = new DatagramPacket(message.getBytes(), message.length(), serveur, port);
		clientSocket.send(dataSent);
		
		DatagramPacket dataRecieved = new DatagramPacket(buffer, taille);
		// recup�rer la requete � partir du socket client 
		clientSocket.receive(dataRecieved);
		
		System.out.println("Message du serveur : " + new String(dataRecieved.getData()).trim());
		System.out.println("From : " + dataRecieved.getAddress() + ":" + dataRecieved.getPort());
		clientSocket.close();
	}
}
