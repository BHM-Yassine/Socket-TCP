package tp2.socket.ex1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Serveur {
	// le port du serveur
	final static int port = 7878;
	// la taille max du paquet
	final static int taille = 1024;
	final static byte buffer[] = new byte[taille];

	public static void main(String argv[]) throws Exception {
		// creer socket serveur
		DatagramSocket serverSocket = new DatagramSocket(port);

		System.out.println("j'attends la connexion d'un client");

		while (true) {
			// Construit un objet pour recevoir un datagramme.
			DatagramPacket datarecv = new DatagramPacket(buffer, taille);

			// recupérer la requete à partir du socket serveur
			serverSocket.receive(datarecv);

			System.out.println(
					"Un client est connecté, son IP est :" + datarecv.getAddress() + " : " + datarecv.getPort());
			System.out.println("Message du Client : " + new String(datarecv.getData()));
			System.out.println("-------------");

			// Message à envoyer
			String message = "Bonjour Mr le client";
			// Construction du datagramme
			DatagramPacket dataSent = new DatagramPacket(message.getBytes(), message.length(), datarecv.getAddress(),
					datarecv.getPort());
			serverSocket.send(dataSent);
			
		}
	}

}
