package tp2.socket.ex2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class Serveur {
	// le port du serveur
	final static int port = 7878;

	// la taille max du paquet
	final static int taille = 1024;
	final static byte buffer[] = new byte[taille];

	public static void main(String argv[]) throws Exception {
		String phrase = "";
		DatagramSocket serverSocket = new DatagramSocket(port);

		System.out.println("j'attends la connexion d'un client");
	
		while (true) {
			DatagramPacket datarecv = new DatagramPacket(buffer, taille);
			
			serverSocket.receive(datarecv);
			System.out.println("Un client est connecté, son IP est :" + datarecv.getAddress());
			System.out.println("Message du Client : " + new String(datarecv.getData()));

			String message = "Bonjour Mr le client";
			String time = "Time : " + new Date();
			serverSocket.send(new DatagramPacket(message.getBytes(), message.length(), datarecv.getAddress(), datarecv.getPort()));
			serverSocket.send(new DatagramPacket(time.getBytes(), time.length(), datarecv.getAddress(), datarecv.getPort()));
			
			
			do {
				String req = "Taper une phrase: ";
				serverSocket.send(new DatagramPacket(req.getBytes(), req.length(), datarecv.getAddress(), datarecv.getPort()));
				
				serverSocket.receive(datarecv);
				phrase = new String(datarecv.getData(), 0, datarecv.getLength());
				
				String reponse = "La phrase en majiscule est : " + phrase.toUpperCase();
				serverSocket.send(new DatagramPacket(reponse.getBytes(), reponse.length(), datarecv.getAddress(), datarecv.getPort()));
				
			} while (!phrase.equals("bye"));

		}
	}
}
