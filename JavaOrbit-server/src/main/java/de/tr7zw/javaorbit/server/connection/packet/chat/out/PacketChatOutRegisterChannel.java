package de.tr7zw.javaorbit.server.connection.packet.chat.out;

import de.tr7zw.javaorbit.server.connection.packet.PacketOut;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PacketChatOutRegisterChannel extends PacketOut{

	private int sort;
	private String name;
	private int id;
	
	@Override
	public StringBuilder buildData() {
		return new StringBuilder("by%").append(id).append("|").append(name).append("|").append(sort).append("|-1#");
	}

}
