package de.tr7zw.javaorbit.server.chat;

import de.tr7zw.javaorbit.server.connection.PlayerConnection;
import de.tr7zw.javaorbit.server.connection.packet.PacketOut;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ChatUser {

	@NonNull private PlayerConnection connection;
	private String userName;
	private String clanTag;
	private String clientLang;
	private int id;
	private boolean connected = false;
	
	public void sendPacket(PacketOut packet) {
		getConnection().send(packet.buildData().toString());
	}
	
}
