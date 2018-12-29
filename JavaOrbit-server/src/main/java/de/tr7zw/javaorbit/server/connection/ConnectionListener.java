package de.tr7zw.javaorbit.server.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import de.tr7zw.javaorbit.server.Server;

public class ConnectionListener implements Runnable{

	private ServerSocket serverSocket;
	
	public ConnectionListener() throws IOException{
		serverSocket = new ServerSocket(8080);
	}

	@Override
	public void run() {
		while(!Server.getInstance().isShutdown()) {
			try {
				Socket socket = serverSocket.accept();
				Server.getInstance().getConnectionPool().addConnection(new PlayerConnection(socket));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
