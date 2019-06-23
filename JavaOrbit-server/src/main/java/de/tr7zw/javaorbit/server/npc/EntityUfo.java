package de.tr7zw.javaorbit.server.npc;

import de.tr7zw.javaorbit.server.Location;
import de.tr7zw.javaorbit.server.enums.Ammo;
import de.tr7zw.javaorbit.server.enums.ShipType;
import de.tr7zw.javaorbit.server.maps.entities.Ship;

public class EntityUfo extends EntityNPC {

	public EntityUfo(Location location) {
		super("-= [ UFO ] =-", new UFOShip(), location);

	}

	public static class UFOShip extends Ship {
		public UFOShip() {
			setType(ShipType.UFO_BIG);
			setMaxHp(1000000);
			setHp(getMaxHp());
		}
	}

	@Override
	public Ammo getAmmo() {
		return Ammo.SANEJIEWS;
	}
}
