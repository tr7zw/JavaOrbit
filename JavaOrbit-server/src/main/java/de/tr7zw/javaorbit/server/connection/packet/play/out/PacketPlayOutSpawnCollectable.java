package de.tr7zw.javaorbit.server.connection.packet.play.out;

import de.tr7zw.javaorbit.server.connection.packet.PacketOut;
import de.tr7zw.javaorbit.server.enums.collectables.Collectable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PacketPlayOutSpawnCollectable extends PacketOut{

	private int id;
	private Collectable type;
	private int posX;
	private int posY;
	
	@Override
	public StringBuilder buildData() {
		return new StringBuilder("0|c|")
				.append(id).append("|")
				.append(type.getId()).append("|")
				.append(posX).append("|")
				.append(posY);
	}

}
