package de.tr7zw.javaorbit.server.npc;

import de.tr7zw.javaorbit.server.Location;
import de.tr7zw.javaorbit.server.enums.Ammo;
import de.tr7zw.javaorbit.server.enums.ShipType;
import de.tr7zw.javaorbit.server.maps.entities.Ship;

public class EntityStreuner extends EntityNPC {

	public EntityStreuner(Location location) {
		super("-= [ Streuner ] =-", new StreunerShip(), location);
		setPassive(true);
	}

	public static class StreunerShip extends Ship {
		public StreunerShip() {
			setType(ShipType.YAMATO);
            setMaxHp(1000);
            setHp(getMaxHp());
            setMaxShield(600);
            setShield(getMaxShield());
			setSpeed(280);
		}

		@Override
		public int getDps() {
			return 20;
		}
	}

	@Override
	public Ammo getAmmo() {
		return Ammo.LCB10;
	}

}
