package tp2.socket.ex2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
	final static int port = 7878;
	final static int taille = 1024;
	final static String HOST = "localhost";
	final static byte buffer[] = new byte[taille];

	public static void main(String argv[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		String phrase = "";

		DatagramSocket clientSocket = new DatagramSocket();
		DatagramPacket datarecv = new DatagramPacket(buffer, taille);

		InetAddress serveur = InetAddress.getByName(HOST);

		String message = "Bonjour Mr le serveur";
		clientSocket.send(new DatagramPacket(message.getBytes(), message.length(), serveur, port));

		clientSocket.receive(datarecv);
		System.out.println("Message du serveur : " + new String(datarecv.getData()));
		clientSocket.receive(datarecv);
		System.out.println("Time : " + new String(datarecv.getData()));

		do {
			clientSocket.receive(datarecv);
			System.out.println(new String(datarecv.getData(), 0, datarecv.getLength()));

			phrase = sc.nextLine();
			clientSocket.send(new DatagramPacket(phrase.getBytes(), phrase.length(), serveur, port));

			clientSocket.receive(datarecv);
			System.out.println(new String(datarecv.getData(), 0, datarecv.getLength()));
		} while (!phrase.equals("bye"));

		clientSocket.close();
	}
}
