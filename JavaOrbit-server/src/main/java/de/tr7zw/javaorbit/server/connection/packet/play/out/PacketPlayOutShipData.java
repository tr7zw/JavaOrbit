package de.tr7zw.javaorbit.server.connection.packet.play.out;

import de.tr7zw.javaorbit.server.connection.packet.PacketOut;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PacketPlayOutShipData extends PacketOut{

	private int id;
	private String name;
	private int hp;
	private int maxHp;
	private int shield;
	private int maxShield;
	
	@Override
	public StringBuilder buildData() {
		return new StringBuilder("0|N|")
				.append(id).append("|")
				.append(name).append("|")
				.append(shield).append("|")
				.append(maxShield).append("|")
				.append(hp).append("|")
				.append(maxHp).append("|");
	}

}
