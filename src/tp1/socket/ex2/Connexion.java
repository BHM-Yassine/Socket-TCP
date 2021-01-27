package tp1.socket.ex2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class Connexion implements Runnable {
	static Date date = new Date();
	private Socket s = null;
	String phrase = "";

	public Connexion(Socket s) {
		this.s = s;
	}

	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter out = new PrintWriter(s.getOutputStream());
			
			String requete = in.readLine();
			System.out.println("le client a envoyé le message : " + requete);
			
			out.println("Bonjour Mr le client");
			out.println("Time : " + date);
			out.flush();
			
			do {
				out.println("Taper une phrase : ");
				out.flush();
				
				phrase = in.readLine();
				
				out.println("La phrase en majiscule est : " + phrase.toUpperCase());
				out.flush();
				
			} while(!phrase.equals("bye"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
