package de.tr7zw.javaorbit.server.connection.packet.out;

import de.tr7zw.javaorbit.server.connection.packet.PacketOut;
import de.tr7zw.javaorbit.server.enums.collectables.Mine;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PacketOutSpawnMine extends PacketOut{

	private int id;
	private Mine mine;
	private int posX;
	private int posY;
	
	@Override
	public StringBuilder buildData() {
		return new StringBuilder("0|L|1|")
				.append(mine.getId()).append("|")
				.append(posX).append("|")
				.append(posY).append("|")
				.append(1).append("|")
				.append(1).append("|").append(1);
	}

}
