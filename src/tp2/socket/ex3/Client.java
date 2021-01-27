package tp2.socket.ex3;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;

public class Client {
	final static int port = 7878;
	final static int taille = 1024;
	final static String HOST = "localhost";
	final static byte buffer[] = new byte[taille];

	public static void main(String args[]) throws Exception {

		DatagramSocket clientSocket = new DatagramSocket();
		DatagramPacket datarecv = new DatagramPacket(buffer, taille);
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);

		InetAddress serveur = InetAddress.getByName(HOST);

		String message = "Bonjour Mr le serveur";
		clientSocket.send(new DatagramPacket(message.getBytes(), message.length(), serveur, port));

		clientSocket.receive(datarecv);
		System.out.println("Message du serveur : " + new String(datarecv.getData()));

		clientSocket.receive(datarecv);
		System.out.println("Time : " + new String(datarecv.getData()));

		Personne p = new Personne("bouhm", "yassine", new Date("1998/12/25"));
		
		objectStream.writeObject(p);
		byte[] byteArray = byteStream.toByteArray();
		clientSocket.send(new DatagramPacket(byteArray, byteArray.length, serveur, port));

		clientSocket.receive(datarecv);
		System.out.println(new String(datarecv.getData(), 0, datarecv.getLength()));

	}
}
