package de.tr7zw.javaorbit.server.connection.packet.play.out;

import de.tr7zw.javaorbit.server.connection.packet.PacketOut;
import de.tr7zw.javaorbit.server.enums.Maps;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PacketPlayOutSetMap extends PacketOut{

	private Maps map;
	
	@Override
	public StringBuilder buildData() {
		return new StringBuilder("0|i|").append(map.getId());
	}

}
