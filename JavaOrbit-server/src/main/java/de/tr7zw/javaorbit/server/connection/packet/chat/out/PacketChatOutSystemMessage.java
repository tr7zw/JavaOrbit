package de.tr7zw.javaorbit.server.connection.packet.chat.out;

import de.tr7zw.javaorbit.server.connection.packet.PacketOut;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PacketChatOutSystemMessage extends PacketOut{

	private String message;
	
	@Override
	public StringBuilder buildData() {
		return new StringBuilder("dq%").append(message).append("#");
	}

}
