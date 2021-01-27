package tp1.socket.ex3;

import java.net.ServerSocket;
import java.net.Socket;

public class ServeurMC {

	final static int PORT = 7878;

	public static void main(String[] args) throws Exception {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);
			System.out.println("j'attends la connexion d'un client");
			
			while (true) {
				Socket socket = serverSocket.accept();				
				System.out.println("Un client est connecté, son IP est :" + socket.getInetAddress());
				
				Connexion c = new Connexion(socket);
				
				Thread instance = new Thread(c);
				instance.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}