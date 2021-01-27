package tp1.socket.ex3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class Connexion implements Runnable {
	private Socket s = null;

	public Connexion(Socket s) {
		this.s = s;
	}

	@Override
	public void run() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter writer = new PrintWriter(s.getOutputStream());
			ObjectInputStream objectReader = new ObjectInputStream(s.getInputStream());

			String requete = reader.readLine();
			System.out.println("Client: " + requete);

			writer.println("Bonjour Mr le client");
			writer.println("Time : " + new Date());
			writer.println("Envoyer l'objet personne");
			writer.flush();

			Personne p = (Personne) objectReader.readObject();

			writer.println("L'age de " + p.getPrénom() + " " + p.getNom() + " est : " + p.calcul_age());
			writer.flush();

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
