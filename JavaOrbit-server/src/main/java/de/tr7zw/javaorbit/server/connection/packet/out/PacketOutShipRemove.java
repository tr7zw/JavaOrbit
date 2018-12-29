package de.tr7zw.javaorbit.server.connection.packet.out;

import de.tr7zw.javaorbit.server.connection.packet.PacketOut;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PacketOutShipRemove extends PacketOut{

	private int id;
	
	@Override
	public StringBuilder buildData() {
		return new StringBuilder("0|R|").append(id);
	}

}
