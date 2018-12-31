package de.tr7zw.javaorbit.server.connection.packet.chat.out;

import de.tr7zw.javaorbit.server.connection.packet.PacketOut;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PacketChatOutSetId extends PacketOut{

	private int id;
	
	@Override
	public StringBuilder buildData() {
		return new StringBuilder("bv%").append(id).append("#");
	}

}
