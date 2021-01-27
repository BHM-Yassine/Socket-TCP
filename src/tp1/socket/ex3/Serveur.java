package tp1.socket.ex3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Serveur {
	final static int PORT = 7878;
	static Date date = new Date();

	public static void main(String args[]) throws Exception {
		// Cr�ation d'un gestionnaire de socket en sp�cifiant le Prot
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("j'attends la connexion d'un client");
		
		// Acceptation de la demande de connexion en cr�ant une nouvelle socket
		Socket socket = serverSocket.accept();
		System.out.println("Un client est connect�, son IP est :" + socket.getInetAddress());
		
		try {
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// Cr�er un flux d�objets pour la r�ception d�un objet
			ObjectInputStream objectReader = new ObjectInputStream(socket.getInputStream()) ;
			
			String requete = reader.readLine();
			System.out.println("Client: " + requete);
			
			writer.println("Bonjour Mr le client");
			writer.println("Time : " + date);
			writer.println("Envoyer l'objet personne");
			writer.flush();
			
			Personne p = (Personne) objectReader.readObject();
			
			writer.println("L'age de " + p.getPr�nom() + " " + p.getNom() + " est : " + p.calcul_age());
			writer.flush();
			
			reader.close();
			writer.close();
			socket.close();
			serverSocket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
