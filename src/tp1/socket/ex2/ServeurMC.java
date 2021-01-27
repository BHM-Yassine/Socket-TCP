package tp1.socket.ex2;

import java.net.ServerSocket;
import java.net.Socket;

public class ServeurMC {

	final static int PORT = 7878;

	public static void main(String[] args) throws Exception {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);
			System.out.println("j'attends la connexion d'un client");
			
			while (true) {

				// accepter une connexion
				Socket socket = serverSocket.accept();
				
				System.out.println("Un client est connecté, son IP est :" + socket.getInetAddress());
				
				// créer un thread : pour échanger les données avec le client
				Connexion c = new Connexion(socket);
				
				Thread instance = new Thread(c);
				// Démarrer le thread
				instance.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
