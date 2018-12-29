package de.tr7zw.javaorbit.server.connection.packet.out;

import de.tr7zw.javaorbit.server.connection.packet.PacketOut;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PacketOutSetDrones extends PacketOut{
	
	private int id;
	
	@Override
	public StringBuilder buildData() {
		return new StringBuilder("0|n|d|").append(id).append("|").append("3/2-25-25,3/4-25-25-25-25,3/2-25-25"); //TODO: generate formations
	}

}
