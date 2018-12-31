package de.tr7zw.javaorbit.server.connection.packet;

import de.tr7zw.javaorbit.server.chat.ChatUser;

public abstract class PacketChatIn extends Packet{

	public PacketChatIn(String data) {
	}

	public abstract void onRecieve(ChatUser user);

	public String clearString(String data) {
		return data.replace("" + (char)0x00, "").replace("\n", "").replace("\r", "");
	}
}
