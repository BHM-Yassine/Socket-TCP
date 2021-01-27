package tp1.socket.ex2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Serveur {
	final static int PORT = 7878;
	static Date date = new Date();

	public static void main(String args[]) throws Exception {
		String phrase = "";
		
		// Cr�ation d'un gestionnaire de socket en sp�cifiant le Prot
		ServerSocket serverSocket = new ServerSocket(PORT);
		
		System.out.println("j'attends la connexion d'un client");
		
		// Acceptation de la demande de connexion en cr�ant une nouvelle socket
		Socket socket = serverSocket.accept();
		
		System.out.println("Un client est connect�, son IP est :" + socket.getInetAddress());
		
		try {
			InputStream IS = socket.getInputStream();
			
			// Cr�ation d�un stream pour convertir les bytes re�us
			InputStreamReader ISR = new InputStreamReader(IS);
			// Cr�ation d�un stream de lecture avec tampon
			BufferedReader reader = new BufferedReader(ISR);
			
			OutputStream OS = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(OS);
			
			// Lire le msg recus
			String requete = reader.readLine();
			System.out.println("le client a envoy� le message : " + requete);
			
			
			writer.println("Bonjour Mr le client");
			writer.println("Time : " + date);
			writer.flush();
			
			do {
				writer.println("Taper une phrase : ");
				writer.flush();
				
				phrase = reader.readLine();
				
				writer.println("La phrase en majiscule est : " + phrase.toUpperCase());
				writer.flush();
				
			} while(!phrase.equals("bye"));
			
			reader.close();
			writer.close();
			socket.close();
			serverSocket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
