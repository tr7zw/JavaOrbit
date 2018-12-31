package de.tr7zw.javaorbit.server.connection.packet.out;

import de.tr7zw.javaorbit.server.connection.packet.PacketOut;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PacketOutLaserStop extends PacketOut{

	private int id;
	private int enemy;
	
	@Override
	public StringBuilder buildData() {
		return new StringBuilder("0|G")
				.append(id).append("|")
				.append(enemy).append("|");
	}

}
