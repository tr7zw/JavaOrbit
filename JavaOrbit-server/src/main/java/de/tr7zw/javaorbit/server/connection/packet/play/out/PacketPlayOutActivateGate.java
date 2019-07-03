package de.tr7zw.javaorbit.server.connection.packet.play.out;

import de.tr7zw.javaorbit.server.connection.packet.PacketOut;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PacketPlayOutActivateGate extends PacketOut{

	private int mapId;
	private int id;
	
	@Override
	public StringBuilder buildData() {
		return new StringBuilder("0|U|").append(mapId).append("|").append(id).append("|");
	}

}
