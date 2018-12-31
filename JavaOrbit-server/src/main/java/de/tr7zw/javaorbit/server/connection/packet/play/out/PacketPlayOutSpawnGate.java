package de.tr7zw.javaorbit.server.connection.packet.play.out;

import de.tr7zw.javaorbit.server.connection.packet.PacketOut;
import de.tr7zw.javaorbit.server.enums.Gate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PacketPlayOutSpawnGate extends PacketOut{

	private int id;
	private Gate gate;
	private int x;
	private int y;
	
	@Override
	public StringBuilder buildData() {
		return new StringBuilder("0|p|").append(id).append("|").append(gate.getId()).append("|32|").append(x).append("|").append(y);
	}

}
