package de.tr7zw.javaorbit.server.connection.packet.play.out;

import de.tr7zw.javaorbit.server.connection.packet.PacketOut;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PacketPlayOutSetDrones extends PacketOut{
	
	private int id;
	private String formation;
	
	@Override
	public StringBuilder buildData() {
		return new StringBuilder("0|n|d|").append(id).append("|").append(formation);
	}

}
