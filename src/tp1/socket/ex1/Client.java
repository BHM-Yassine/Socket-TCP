package tp1.socket.ex1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	final static int PORT = 7878;
	final static String HOST = "localhost";

	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		String phrase = "";
		
		try {
			// Création d'une socket en spécifiant @IP:Prot
			Socket socket = new Socket(HOST, PORT);
			System.out.println("j'ai envoyé la requête de connexion via le port N°" + socket.getLocalPort());
			
			// Creation d'un flux de donnée sortant
			OutputStream OS = socket.getOutputStream();
			// Vider le tampon, pour le forcer à envoyer toutes les données.
			PrintWriter writer = new PrintWriter(OS);
			
			// Creation d'un flux de donnée entrant
			InputStream IS = socket.getInputStream();
			InputStreamReader ISR = new InputStreamReader(IS);
			BufferedReader reader = new BufferedReader(ISR);
			
			String requete = "Bonjour Mr le serveur";
			writer.println(requete);
			writer.flush();
		
			String reponse = reader.readLine();
			String time = reader.readLine();
			System.out.println("Le serveur m'a répondu par le message : " + reponse + " // " + time);
			
			do {
				System.out.println(reader.readLine());
				
				phrase = sc.nextLine();
				writer.println(phrase);
				writer.flush();
				
				System.out.println(reader.readLine());
				
			} while(!phrase.equals("bye"));
			
			writer.close();
			reader.close();
			socket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
