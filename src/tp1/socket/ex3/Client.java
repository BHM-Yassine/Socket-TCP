package tp1.socket.ex3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class Client {
	final static int PORT = 7878;
	final static String HOST = "localhost";

	public static void main(String args[]) throws Exception {

		try {
			// Création d'une socket en spécifiant @IP:Prot
			Socket socket = new Socket(HOST, PORT);
			System.out.println("j'ai envoyé la requête de connexion via le port N°" + socket.getLocalPort());

			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			// Créer un flux d’objets pour l’envoie d’un objet
			ObjectOutputStream objectWriter = new ObjectOutputStream(socket.getOutputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String requete = "Bonjour Mr le serveur";
			writer.println(requete);
			// Vider le tampon, pour le forcer à envoyer toutes les données.
			writer.flush();

			String reponse = reader.readLine();
			String time = reader.readLine();
			String obj = reader.readLine();
			System.out.println("Serveur: " + reponse);
			System.out.println("Serveur: " + time);
			System.out.println("Serveur: " + obj);

			
			Personne p = new Personne("bouhm", "yassine", new Date("1998/12/25"));
			objectWriter.writeObject(p);

			String msgAge = reader.readLine();
			System.out.println("Serveur: " + msgAge);

			writer.close();
			reader.close();
			socket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
