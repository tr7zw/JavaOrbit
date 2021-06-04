//package de.tr7zw.javaorbit.server.npc;
//
//import de.tr7zw.javaorbit.server.Location;
//import de.tr7zw.javaorbit.server.enums.Ammo;
//import de.tr7zw.javaorbit.server.enums.ShipType;
//import de.tr7zw.javaorbit.server.maps.entities.Ship;
//
//public class EntityUfo extends EntityNPC {
//
//	public EntityUfo(Location location) {
//		super("-= [ UFO ] =-", new UFOShip(), location);
//
//	}
//
//	public static class UFOShip extends Ship {
//		public UFOShip() {
//			setType(ShipType.UFO_BIG);
//			setMaxHp(60000);
//			setHp(getMaxHp());
//			setMaxShield(10000);
//			setShield(getMaxShield());
//			setSpeed(200);
//		}
//
//		@Override
//		public int getDps() {
//			return 10000;
//		}
//	}
//
//	@Override
//	public Ammo getAmmo() {
//		return Ammo.SANEJIEWS;
//	}
//
//}
