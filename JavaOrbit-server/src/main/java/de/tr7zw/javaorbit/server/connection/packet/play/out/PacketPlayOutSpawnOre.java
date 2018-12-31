package de.tr7zw.javaorbit.server.connection.packet.play.out;

import de.tr7zw.javaorbit.server.connection.packet.PacketOut;
import de.tr7zw.javaorbit.server.enums.collectables.Ore;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PacketPlayOutSpawnOre extends PacketOut{

	private int id;
	private Ore ore;
	private int posX;
	private int posY;
	
	@Override
	public StringBuilder buildData() {
		return new StringBuilder("0|r|1|")
				.append(ore.getId()).append("|")
				.append(posX).append("|")
				.append(posY).append("|")
				.append(1).append("|")
				.append(1).append("|").append(1);
	}

}
