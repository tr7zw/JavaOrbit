package de.tr7zw.javaorbit.server.connection.packet.play.out;

import de.tr7zw.javaorbit.server.connection.packet.PacketOut;
import de.tr7zw.javaorbit.server.enums.Ammo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PacketPlayOutShootLaser extends PacketOut{

	private int id;
	private int target;
	private Ammo ammo;
	private boolean enemyHasShield;
	private boolean bigLasers;
	
	@Override
	public StringBuilder buildData() {
		return new StringBuilder("0|a|")
				.append(id).append("|")
				.append(target).append("|")
				.append(ammo.getId()).append("|")
				.append(toEnum(enemyHasShield)).append("|")
				.append(toEnum(bigLasers));
	}

}
