package de.tr7zw.javaorbit.server.connection.packet.out;

import de.tr7zw.javaorbit.server.connection.Faction;
import de.tr7zw.javaorbit.server.connection.packet.PacketOut;
import de.tr7zw.javaorbit.server.enums.Rank;
import de.tr7zw.javaorbit.server.enums.Rings;
import de.tr7zw.javaorbit.server.enums.ShipType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PacketOutSpawnShip extends PacketOut{

	private int id;
	private ShipType ship;
	private String name;
	private int posX;
	private int posY;
	private Faction faction;
	private boolean friendly;
	private Rank rank;
	private Rings rings;
	
	@Override
	public StringBuilder buildData() {
		return new StringBuilder("0|C|")
				.append(id).append("|")
				.append(ship.getId()).append("|0||")
				.append(name).append("|")
				.append(posX).append("|")
				.append(posY).append("|")
				.append(faction.getId()).append("|")//firma
				.append(toEnum(friendly)).append("|")//Friendly
				.append(rank.getId()).append("|")//rang
				.append("1").append("|")//??
				.append("1").append("|")//??
				.append(rings.getId()).append("|")//Ringe
				.append("1").append("|");//??
	}
	
	public static String toEnum(boolean isTrue)
	{
		return (isTrue) ? "1" : "0";
	}

}
