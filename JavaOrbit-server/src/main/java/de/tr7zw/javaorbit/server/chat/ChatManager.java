package de.tr7zw.javaorbit.server.chat;

import java.util.HashMap;

import de.tr7zw.javaorbit.server.connection.packet.chat.out.PacketChatOutSystemMessage;
import de.tr7zw.javaorbit.server.connection.packet.chat.out.PacketChatOutSetId;

public class ChatManager {

	private HashMap<Integer, ChatUser> users = new HashMap<>();
	private HashMap<Integer, ChatChannel> publicChannels = new HashMap<>();
	
	public ChatManager() {
		addChannel(new ChatChannel(0, 1, "Global"));//a%1%test@
		addChannel(new ChatChannel(1, 2, "MMO"));//a%2%mmo@
		addChannel(new ChatChannel(2, 3, "EIC"));
		addChannel(new ChatChannel(3, 4, "VRU"));
	}
	
	public void addChannel(ChatChannel channel) {
		publicChannels.put(channel.getId(), channel);
		for(ChatUser user : users.values())
			channel.addUser(user);
	}
	
	public void addUser(ChatUser user) {
		users.put(user.getId(), user);
		user.sendPacket(new PacketChatOutSetId(user.getId()));
		for(ChatChannel channel : publicChannels.values()) {
			channel.addUser(user);
		}
		String welcome = "\n<span class='mod'>Welcome to <a class='highlight' target=\"_blank\" href=\"http://www.github.com/tr7zw/JavaOrbit\">Java Orbit</a> made by <a class='highlight' target=\"_blank\" href=\"http://www.github.com/tr7zw\">tr7zw.</a>\n</span>";
		user.sendPacket(new PacketChatOutSystemMessage(welcome));
	}
	
	public void removeUser(ChatUser user) {
		users.remove(user.getId());
		for(ChatChannel channel : publicChannels.values()) {
			channel.removeUser(user);
		}
	}
	
	public void sendMessage(ChatUser user, int channelId, String msg) {
		ChatChannel channel = publicChannels.get(channelId);
		if(channel != null) {
			channel.chat(user, msg);
		}
	}
	
}
