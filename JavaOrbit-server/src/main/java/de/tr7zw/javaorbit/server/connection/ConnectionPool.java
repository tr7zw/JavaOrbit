package de.tr7zw.javaorbit.server.connection;

import java.util.HashMap;

public class ConnectionPool {

	private HashMap<Integer, PlayerConnection> connections = new HashMap<>();
	
	public void addConnection(PlayerConnection con) {
		connections.put(con.getId(), con);
		Thread thread = new Thread(con);
		con.setThread(thread);
		thread.start();
	}
	
}
