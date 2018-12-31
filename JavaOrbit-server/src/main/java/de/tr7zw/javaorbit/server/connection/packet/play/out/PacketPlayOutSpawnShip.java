package de.tr7zw.javaorbit.server.connection.packet.play.out;

import de.tr7zw.javaorbit.server.connection.Faction;
import de.tr7zw.javaorbit.server.connection.packet.PacketOut;
import de.tr7zw.javaorbit.server.enums.ClanStatus;
import de.tr7zw.javaorbit.server.enums.Rank;
import de.tr7zw.javaorbit.server.enums.Rings;
import de.tr7zw.javaorbit.server.enums.ShipType;
import de.tr7zw.javaorbit.server.player.Clan;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PacketPlayOutSpawnShip extends PacketOut{

	private int id;
	private ShipType ship;
	private String name;
	private int posX;
	private int posY;
	private Faction faction;
	private boolean friendly;
	private boolean squareOnMinimap;
	private Rank rank;
	private Rings rings;
	private ClanStatus clanStatus;
	private Clan clan;
	
	@Override
	public StringBuilder buildData() {
		return new StringBuilder("0|C|")
				.append(id).append("|")
				.append(ship.getId()).append("|")
				.append("0").append("|")//Laserlook
				.append(clan != null ? clan.getTag() : "").append("|")
				.append(name).append("|")
				.append(posX).append("|")
				.append(posY).append("|")
				.append(faction.getId()).append("|")//firma
				.append(clan != null ? clan.getId() : "").append("|")//Friendly
				.append(rank.getId()).append("|")//rang
				.append(toEnum(squareOnMinimap)).append("|")//??
				.append(clanStatus.getId()).append("|")//??
				.append(rings.getId()).append("|")//Ringe
				.append(toEnum(!friendly)).append("|");//??
	}

}
