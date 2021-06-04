package de.tr7zw.javaorbit.server;

import java.io.IOException;
import java.util.logging.Level;

import org.pf4j.PluginManager;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import de.tr7zw.javaorbit.server.chat.ChatManager;
import de.tr7zw.javaorbit.server.connection.ConnectionListener;
import de.tr7zw.javaorbit.server.connection.ConnectionPool;
import de.tr7zw.javaorbit.server.maps.MapManager;
import lombok.Getter;
import lombok.extern.java.Log;

@Log
public class Server implements Runnable {

	@Getter
	private static Server instance;
	private static Thread serverThread;
	@Getter
	private boolean shutdown = false;
	@Getter
	private ConnectionListener connectionListener;
	@Getter
	private ConnectionListener connectionListenerRetro;    
	@Getter
    private ConnectionListener connectionListenerPolicyServer;
	private Thread connectionThread;
	@Getter
	private ConnectionPool connectionPool;
	@Getter
	private MapManager mapManager;
	@Getter
	private ChatManager chatManager;
	@Inject
	private PluginManager pluginManager;
	@Getter
	@Inject
	private Injector injector;

	public static void main(String[] args) throws InterruptedException {
		Injector injector = Guice.createInjector(new InjectorModule());
		Server server = injector.getInstance(Server.class);
		serverThread = new Thread(server);
		serverThread.start();
	}

	public Server() {
		instance = this;
	}

	@Override
	public void run() {
		pluginManager.loadPlugins();
		try {
			this.connectionListener = new ConnectionListener(8080);
			connectionThread = new Thread(connectionListener);
			connectionThread.start();
			this.connectionPool = new ConnectionPool();
			this.connectionListenerRetro = new ConnectionListener(5152);
			new Thread(connectionListenerRetro).start();
			this.connectionListenerPolicyServer = new ConnectionListener(843);
            new Thread(connectionListenerPolicyServer).start();
		} catch (IOException e) {
			log.log(Level.SEVERE, "Unable to start the connection listener!", e);
			return;
		}
		mapManager = new MapManager();
		chatManager = new ChatManager();
		pluginManager.startPlugins();

		log.log(Level.INFO, "Server Started!");
		while (!shutdown) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
