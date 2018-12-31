package de.tr7zw.javaorbit.server.chat;

import java.util.HashMap;

import de.tr7zw.javaorbit.server.connection.packet.PacketOut;
import de.tr7zw.javaorbit.server.connection.packet.chat.out.PacketChatOutPlayerMessage;
import de.tr7zw.javaorbit.server.connection.packet.chat.out.PacketChatOutRegisterChannel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChatChannel {

	@NonNull @Getter private Integer id;
	@NonNull @Getter private Integer sorting;
	@NonNull @Getter private String name;
	private HashMap<Integer, ChatUser> users = new HashMap<>();
	
	public void addUser(ChatUser user) {
		users.put(user.getId(), user);
		user.sendPacket(new PacketChatOutRegisterChannel(id, name, sorting));
	}
	
	public void removeUser(ChatUser user) {
		users.remove(user.getId());
	}
	
	public void chat(ChatUser user, String message) {
		if(users.containsKey(user.getId())) {
			PacketOut packet = new PacketChatOutPlayerMessage(id, user.getUserName(), message);
			for(ChatUser member : users.values()) {
				member.sendPacket(packet);
			}
		}
	}
	
}
