package de.tr7zw.javaorbit.server;

import java.io.IOException;
import java.net.BindException;
import java.util.logging.Level;

import de.tr7zw.javaorbit.cms.CmsEmbededRunner;
import de.tr7zw.javaorbit.server.chat.ChatManager;
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
	@Getter
	private ConnectionListener connectionListenerRetro;
	private Thread connectionThread;
	@Getter
	private ConnectionPool connectionPool;
	@Getter
	private MapManager mapManager;
	@Getter
	private ChatManager chatManager;
	@Getter
	private CmsEmbededRunner cms;
	
	public static void main(String[] args) throws InterruptedException {
		new Server();
		while(!instance.shutdown) {
			Thread.sleep(100);
		}
	}
	
	public Server() {
		instance = this;
		try {
			this.connectionListener = new ConnectionListener(8080);
			connectionThread = new Thread(connectionListener);
			connectionThread.start();
			this.connectionPool = new ConnectionPool();
			this.connectionListenerRetro = new ConnectionListener(5152);
			new Thread(connectionListenerRetro).start();
		} catch (IOException e) {
			log.log(Level.SEVERE, "Unable to start the connection listener!", e);
			return;
		}
		mapManager = new MapManager();
		chatManager = new ChatManager();
		cms = new CmsEmbededRunner();
		try {
			cms.startServer();
		}catch(BindException ex) {
			log.log(Level.SEVERE, "Unable to bind to port!", ex);
			return;
		}
		log.log(Level.INFO, "Server Started!");
	}

}
