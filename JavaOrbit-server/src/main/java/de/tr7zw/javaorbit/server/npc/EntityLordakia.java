//package de.tr7zw.javaorbit.server.npc;
//
//import de.tr7zw.javaorbit.server.Location;
//import de.tr7zw.javaorbit.server.enums.Ammo;
//import de.tr7zw.javaorbit.server.enums.ShipType;
//import de.tr7zw.javaorbit.server.maps.entities.EntityLiving;
//import de.tr7zw.javaorbit.server.maps.entities.Ship;
//
//public class EntityLordakia extends EntityNPC {
//
//    public EntityLordakia(Location location) {
//        super("-= [ Lordakia ] =-", new LordakiaShip(), location);
//    }
//
//    public static class LordakiaShip extends Ship {
//        public LordakiaShip() {
//            setType(ShipType.LORDAKIA);
//            setMaxHp(2000);
//            setHp(getMaxHp());
//            setMaxShield(2000);
//            setShield(getMaxShield());
//            setSpeed(320);
//        }
//
//        @Override
//        public int getDps() {
//            return 80;
//        }
//    }
//
//    @Override
//    public Ammo getAmmo() {
//        return Ammo.LCB10;
//    }
//
//}
