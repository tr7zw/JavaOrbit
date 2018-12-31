package de.tr7zw.javaorbit.server.connection.packet.chat.out;

import de.tr7zw.javaorbit.server.connection.packet.PacketOut;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PacketChatOutPlayerMessage extends PacketOut{

	private int channelId;
	private String userName;
	private String text;
	
	@Override
	public StringBuilder buildData() {
		return new StringBuilder("j%").append(channelId).append("@").append(userName).append("@").append(text).append("@3#");
	}

}
