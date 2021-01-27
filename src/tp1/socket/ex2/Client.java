package tp1.socket.ex2;

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
			// Cr�ation d'une socket en sp�cifiant @IP:Prot
			Socket socket = new Socket(HOST, PORT);
			System.out.println("j'ai envoy� la requ�te de connexion via le port N�" + socket.getLocalPort());
			
			// Creation d'un flux de donn�e
			OutputStream OS = socket.getOutputStream();
			
			// Vider le tampon, pour le forcer � envoyer toutes les donn�es.
			PrintWriter writer = new PrintWriter(OS);
			String requete = "Bonjour Mr le serveur";
			writer.println(requete);
			writer.flush();
			
			InputStream IS = socket.getInputStream();
			// Cr�ation d�un stream pour convertir les bytes re�us
			InputStreamReader ISR = new InputStreamReader(IS);
			// Cr�ation d�un stream de lecture avec tampon
			BufferedReader reader = new BufferedReader(ISR);
			String reponse = reader.readLine();
			String time = reader.readLine();
			System.out.println("Le serveur m'a r�pondu par le message : " + reponse + " // " + time);
			
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
