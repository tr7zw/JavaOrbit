package de.tr7zw.javaorbit.server.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;

import de.tr7zw.javaorbit.server.Server;
import de.tr7zw.javaorbit.server.chat.ChatUser;
import de.tr7zw.javaorbit.server.connection.packet.Packet;
import de.tr7zw.javaorbit.server.connection.packet.PacketChatIn;
import de.tr7zw.javaorbit.server.connection.packet.PacketPlayIn;
import de.tr7zw.javaorbit.server.connection.packet.chat.in.PacketChatInInit;
import de.tr7zw.javaorbit.server.connection.packet.PacketParser;
import de.tr7zw.javaorbit.server.connection.packet.play.in.PacketPlayInLogin;
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
	@Getter
	private ChatUser chatUser;

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
				if(!message.contains("" + (char)0x00)) {//Doesn't work with chat
					//continue;
				}
				for(String data : message.split("" + (char)0x00)){
					try{
						parsePacket(data);
					}catch(Exception ex){
						ex.printStackTrace();
					}
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
		if(chatUser != null) {
			Server.getInstance().getChatManager().removeUser(chatUser);
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
		data = data.replace("\n", "");
		Packet packet = PacketParser.parse(data);
		if(packet == null)return;
		if(chatUser != null) {
			if(packet instanceof PacketPlayIn) {//wtf? No Play packets from the chat connection!
				log.log(Level.WARNING, "The Chatuser " + chatUser.getUserName() + " sent a Play Packet: " + packet.getClass().getSimpleName());
				disconnect();
				return;
			}
			PacketChatIn chatPacket = (PacketChatIn) packet;
			chatPacket.onRecieve(chatUser);
			return;
		}
		if(player == null) {
			if(packet instanceof PacketPlayInLogin) {
				PacketPlayInLogin login = (PacketPlayInLogin) packet;
				player = new Player(this, new Session(login.getUserId(), login.getSessionToken(), login.getVersion())); 
				//TODO: Validate session
				player.sendLogin();
				return;
			}else if(packet instanceof PacketChatInInit){
				ChatUser chatUser = new ChatUser(this);
				PacketChatInInit init = (PacketChatInInit) packet;
				init.onRecieve(chatUser);
				if(chatUser.isConnected()) {
					this.chatUser = chatUser;
					log.log(Level.INFO, "Chatuser " + chatUser.getUserName() +  " connected!");
					Server.getInstance().getChatManager().addUser(chatUser);
				}else {
					disconnect();
					return;
				}
			}else {
				disconnect();
				return;
			}
		}else {
			if(packet instanceof PacketPlayIn) {
				PacketPlayIn in = (PacketPlayIn) packet;
				in.onRecieve(getPlayer());
			}else {
				System.out.println(packet.getClass().getSimpleName());
			}
		}
	}

}
