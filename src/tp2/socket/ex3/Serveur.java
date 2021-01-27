package tp2.socket.ex3;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Serveur {
	final static int port = 7878;
	final static int taille = 1024;
	final static byte buffer[] = new byte[taille];

	public static void main(String args[]) throws Exception {
		DatagramSocket serverSocket = new DatagramSocket(port);

		System.out.println("j'attends la connexion d'un client");

		while (true) {
			DatagramPacket datarecv = new DatagramPacket(buffer, taille);

			serverSocket.receive(datarecv);
			System.out.println("Un client est connecté, son IP est :" + datarecv.getAddress());
			System.out.println("Message du Client : " + new String(datarecv.getData()));

			String message = "Bonjour Mr le client";
			String time = "Time : " + new Date();
			serverSocket.send(new DatagramPacket(message.getBytes(), message.length(), datarecv.getAddress(),
					datarecv.getPort()));
			serverSocket.send(
					new DatagramPacket(time.getBytes(), time.length(), datarecv.getAddress(), datarecv.getPort()));

			serverSocket.receive(datarecv);
			ByteArrayInputStream byteStream = new ByteArrayInputStream(datarecv.getData());
			ObjectInputStream objectStream = new ObjectInputStream(byteStream);
			Personne p = (Personne) objectStream.readObject();

			String reponse = "L'age de " + p.getPrénom() + " " + p.getNom() + " est : " + p.calcul_age();
			serverSocket.send(new DatagramPacket(reponse.getBytes(), reponse.length(), datarecv.getAddress(),
					datarecv.getPort()));

		}
	}

}
