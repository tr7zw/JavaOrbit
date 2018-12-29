package de.tr7zw.javaorbit.server.connection.packet.out;

import de.tr7zw.javaorbit.server.connection.packet.PacketOut;
import de.tr7zw.javaorbit.server.enums.Station;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PacketOutSpawnStation extends PacketOut{

	private String name;
	private Station station;
	private int x;
	private int y;
	
	@Override
	public StringBuilder buildData() {
		return new StringBuilder("0|s|0|1|").append(name).append("|").append(station.getId()).append("|0|").append(x).append("|").append(y);
	}

}
