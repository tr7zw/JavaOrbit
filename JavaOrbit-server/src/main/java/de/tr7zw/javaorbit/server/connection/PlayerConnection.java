package de.tr7zw.javaorbit.server.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

import de.tr7zw.javaorbit.server.Server;
import de.tr7zw.javaorbit.server.connection.packet.Packet;
import de.tr7zw.javaorbit.server.connection.packet.PacketIn;
import de.tr7zw.javaorbit.server.connection.packet.PacketParser;
import de.tr7zw.javaorbit.server.connection.packet.in.PacketInLogin;
import de.tr7zw.javaorbit.server.player.Player;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.java.Log;

@Log
public class PlayerConnection implements Runnable{

	private static AtomicInteger ID_COUNTER = new AtomicInteger(0);

	@Getter
	private int id;
	@Getter @Setter
	private Thread thread;
	@NonNull
	private Socket socket;
	private BufferedReader bufferedReader;
	private PrintWriter printWriter;

	@Getter
	private Player player;

	public PlayerConnection(@NonNull Socket socket) throws IOException {
		this.id = ID_COUNTER.incrementAndGet();
		this.socket = socket;
		bufferedReader = 
				new BufferedReader(
						new InputStreamReader(
								socket.getInputStream()));
		printWriter =
				new PrintWriter(
						new OutputStreamWriter(
								socket.getOutputStream()));
		log.info("Accepted connection ID: " + id);
	}

	@Override
	public void run() {
		while(socket.isConnected()) {
			try {
				char[] buffer = new char[2048];
				int read = bufferedReader.read(buffer, 0, 2048);
				if(read <= 0) {
					disconnect();
					return;
				}
				String message = new String(buffer, 0, read);
				if(message.startsWith("<policy-file-request/>")) {
					send("<?xml version=\"1.0\"?><cross-domain-policy xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"http://www.adobe.com/xml/schemas/PolicyFileSocket.xsd\"><allow-access-from domain=\"*\" to-ports=\"*\" secure=\"false\" /><site-control permitted-cross-domain-policies=\"master-only\" /></cross-domain-policy>");
					disconnect();
					continue;
				}
				if(!message.contains("" + (char)0x00)) {
					continue;
				}
				for(String data : message.split("" + (char)0x00)){
					parsePacket(data);
				}
			} catch (IOException e) {
				if(socket.isClosed())break;
				e.printStackTrace();
			}
		}
		disconnect();
	}

	public void disconnect() {
		try {
			if(socket.isConnected())
				socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(player != null) {
			Server.getInstance().getMapManager().removePlayer(player);
		}
		log.info("Closed connection ID: " + id);
	}

	public void send(String header, String data) {
		data = "0|" + header + "|" + data;
		if(socket.isConnected()) {
			String[] requests = data.split("" + (char)0x00);
			for(String s : requests) {
				//System.out.println("Sent: " + s);
				printWriter.print(s + (char)0x00);
				printWriter.flush();
			}
		}
	}
	
	public void send(String data) {
		if(socket.isConnected()) {
			String[] requests = data.split("" + (char)0x00);
			for(String s : requests) {
				//System.out.println("Sent: " + s);
				printWriter.print(s + (char)0x00);
				printWriter.flush();
			}
		}
	}

	public void parsePacket(String data) {
		Packet packet = PacketParser.parse(data);
		if(packet == null)return;
		if(player == null) {
			if(packet instanceof PacketInLogin) {
				PacketInLogin login = (PacketInLogin) packet;
				player = new Player(this, new Session(login.getUserId(), login.getSessionToken(), login.getVersion())); 
				//TODO: Validate session
				player.sendLogin();
				return;
			}else {
				disconnect();
			}
		}else {
			if(packet instanceof PacketIn) {
				PacketIn in = (PacketIn) packet;
				in.onRecieve(getPlayer());
			}else {
				System.out.println(packet.getClass().getSimpleName());
			}
		}
	}

}
