package de.tr7zw.javaorbit.server.connection.packet.out;

import de.tr7zw.javaorbit.server.connection.packet.PacketOut;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PacketOutShipMove extends PacketOut{

	private int id;
	private int x;
	private int y;
	private int time;
	
	@Override
	public StringBuilder buildData() {
		return new StringBuilder("0|1|")
				.append(id).append("|")
				.append(x).append("|")
				.append(y).append("|")
				.append(time);
	}

}
