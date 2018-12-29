package de.tr7zw.javaorbit.server;

import java.io.IOException;
import java.util.logging.Level;

import de.tr7zw.javaorbit.server.connection.ConnectionListener;
import de.tr7zw.javaorbit.server.connection.ConnectionPool;
import de.tr7zw.javaorbit.server.maps.MapManager;
import lombok.Getter;
import lombok.extern.java.Log;

@Log
public class Server {

	@Getter
	private static Server instance;
	@Getter
	private boolean shutdown = false;
	@Getter
	private ConnectionListener connectionListener;
	private Thread connectionThread;
	@Getter
	private ConnectionPool connectionPool;
	@Getter
	private MapManager mapManager;
	
	public static void main(String[] args) throws InterruptedException {
		new Server();
		while(!instance.shutdown) {
			Thread.sleep(100);
		}
	}
	
	public Server() {
		instance = this;
		try {
			this.connectionListener = new ConnectionListener();
			connectionThread = new Thread(connectionListener);
			connectionThread.start();
			this.connectionPool = new ConnectionPool();
		} catch (IOException e) {
			log.log(Level.SEVERE, "Unable to start the connection listener!", e);
			return;
		}
		mapManager = new MapManager();
	}

}
