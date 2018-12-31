package de.tr7zw.javaorbit.server.connection.packet.play.out;

import de.tr7zw.javaorbit.server.connection.Faction;
import de.tr7zw.javaorbit.server.connection.packet.PacketOut;
import de.tr7zw.javaorbit.server.enums.Maps;
import de.tr7zw.javaorbit.server.enums.Rank;
import de.tr7zw.javaorbit.server.enums.Rings;
import de.tr7zw.javaorbit.server.enums.ShipType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PacketPlayOutPlayerInfo extends PacketOut{

	private int userId;
	private String name;
	private ShipType shipType;
	private int speed;
	private int shield;
	private int maxShield;
	private int hp;
	private int maxHp;
	private int cargo;
	private int maxCargo;
	private int posX;
	private int posY;
	private Maps map;
	private Faction faction;
	private int clanId;
	private int maxLaser;
	private int maxRocket;
	private int laserState;
	private boolean premium;
	private int exp;
	private int honour;
	private int level;
	private int credits;
	private int uri;
	private int jackpot;
	private Rank rank;
	private String clanTag;
	private Rings rings;
	private boolean invisible;
	
	@Override
	public StringBuilder buildData() {
		return new StringBuilder("RDY|I|")
				.append(userId).append("|")
				.append(name).append("|")
				.append(shipType.getId()).append("|")
				.append(speed).append("|")
				.append(shield).append("|")
				.append(maxShield).append("|")
				.append(hp).append("|")
				.append(maxHp).append("|")
				.append(cargo).append("|")
				.append(maxCargo).append("|")
				.append(posX).append("|")
				.append(posY).append("|")
				.append(map.getId()).append("|")
				.append(faction.getId()).append("|")
				.append(clanId).append("|")
				.append(maxLaser).append("|")
				.append(maxRocket).append("|")
				.append(laserState).append("|")
				.append(toEnum(premium)).append("|")
				.append(exp).append("|")
				.append(honour).append("|")
				.append(level).append("|")
				.append(credits).append("|")
				.append(uri).append("|")
				.append(jackpot).append("|")
				.append(rank.getId()).append("|")
				.append(clanTag).append("|")
				.append(rings.getId()).append("|")
				.append(toEnum(invisible)).append("|");
	}
	
	public static String toEnum(boolean isTrue)
	{
		return (isTrue) ? "1" : "0";
	}

}
