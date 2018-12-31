package de.tr7zw.javaorbit.server.connection;

import de.tr7zw.javaorbit.server.connection.packet.Direction;
import de.tr7zw.javaorbit.server.connection.packet.Packet;
import de.tr7zw.javaorbit.server.connection.packet.in.PacketInAttackRocket;
import de.tr7zw.javaorbit.server.connection.packet.in.PacketInBuy;
import de.tr7zw.javaorbit.server.connection.packet.in.PacketInChangeConfig;
import de.tr7zw.javaorbit.server.connection.packet.in.PacketInClientSetting;
import de.tr7zw.javaorbit.server.connection.packet.in.PacketInJump;
import de.tr7zw.javaorbit.server.connection.packet.in.PacketInLaserAttack;
import de.tr7zw.javaorbit.server.connection.packet.in.PacketInLaserStop;
import de.tr7zw.javaorbit.server.connection.packet.in.PacketInLogin;
import de.tr7zw.javaorbit.server.connection.packet.in.PacketInLogout;
import de.tr7zw.javaorbit.server.connection.packet.in.PacketInLogoutCancel;
import de.tr7zw.javaorbit.server.connection.packet.in.PacketInMove;
import de.tr7zw.javaorbit.server.connection.packet.in.PacketInPing;
import de.tr7zw.javaorbit.server.connection.packet.in.PacketInReady;
import de.tr7zw.javaorbit.server.connection.packet.in.PacketInSelect;
import de.tr7zw.javaorbit.server.connection.packet.in.PacketInSelectAmmo;
import de.tr7zw.javaorbit.server.connection.packet.in.PacketInSelectRocket;
import de.tr7zw.javaorbit.server.connection.packet.in.PacketInSelectShip;
import de.tr7zw.javaorbit.server.connection.packet.in.PacketInTech;
import de.tr7zw.javaorbit.server.connection.packet.in.admin.PacketInAdminRank;
import de.tr7zw.javaorbit.server.connection.packet.in.admin.PacketInAdminShip;
import de.tr7zw.javaorbit.server.connection.packet.in.admin.PacketInAdminTP;
import de.tr7zw.javaorbit.server.connection.packet.in.admin.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PacketType {
	UNKNOWN(Direction.IN, "UNKNOWN", null),
	
	//IN
LOGIN(Direction.IN, "LOGIN", PacketInLogin.class),
PING(Direction.IN, "PNG", PacketInPing.class),
JUMP(Direction.IN, "j", PacketInJump.class),
MOVE(Direction.IN, "1", PacketInMove.class),
READY(Direction.IN, "RDY", PacketInReady.class),
SETTING(Direction.IN, "7", PacketInClientSetting.class),
ATTACK_LASER(Direction.IN, "a", PacketInLaserAttack.class),
STOP_LASER(Direction.IN, "G", PacketInLaserStop.class),
SELECT_AMMO(Direction.IN , "u", PacketInSelectAmmo.class),
SELECT_ROCKET(Direction.IN , "d", PacketInSelectRocket.class),
ATTACK_ROCKET(Direction.IN, "v", PacketInAttackRocket.class),
CHANGE_CONFIG(Direction.IN, "CFG", PacketInChangeConfig.class),
LOGOUT(Direction.IN, "l", PacketInLogout.class),
LOGOUT_CANCEL(Direction.IN, "o", PacketInLogoutCancel.class),
BUY(Direction.IN, "5", PacketInBuy.class),
SELECT_SHIP(Direction.IN, "SEL", PacketInSelectShip.class),
SELECT(Direction.IN, "S", PacketInSelect.class),
TECH(Direction.IN, "TX", PacketInTech.class),

//IN Admin
ADMIN_RANK(Direction.IN, "/RANK", PacketInAdminRank.class),
ADMIN_WARP(Direction.IN, "/WARP", PacketInAdminWarp.class),
ADMIN_SHIP(Direction.IN, "/SHIP", PacketInAdminShip.class),
ADMIN_TP(Direction.IN, "/TP", PacketInAdminTP.class),
ADMIN_SPAWN(Direction.IN, "/SPAWN", PacketInAdminSpawn.class),
ADMIN_SPAWN_COLLECTABLE(Direction.IN, "/BOX", PacketInAdminCollectable.class),
ADMIN_SPAWN_BOMB(Direction.IN, "/BOMB", PacketInAdminBomb.class),
ADMIN_POS(Direction.IN, "/POS", PacketInAdminPos.class),

//Chat
//Chat_Init: bu
//getuserlist: bx
//userJoin: bz


	;
	private Direction direction;
	private String packetId;
	private Class<? extends Packet> handlerClass;
	
	public static PacketType getType(String data) {
		for(PacketType type : values()) {
			if(data.startsWith(type.getPacketId())) {
				return type;
			}
			if(data.toUpperCase().startsWith(type.getPacketId())) {
				return type;
			}
		}
		return UNKNOWN;
	}
}
