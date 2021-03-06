package de.tr7zw.javaorbit.server.connection;

import de.tr7zw.javaorbit.server.connection.packet.Direction;
import de.tr7zw.javaorbit.server.connection.packet.Packet;
import de.tr7zw.javaorbit.server.connection.packet.chat.in.PacketChatInChatting;
import de.tr7zw.javaorbit.server.connection.packet.chat.in.PacketChatInInit;
import de.tr7zw.javaorbit.server.connection.packet.play.in.admin.PacketInAdminBomb;
import de.tr7zw.javaorbit.server.connection.packet.play.in.admin.PacketInAdminClan;
import de.tr7zw.javaorbit.server.connection.packet.play.in.admin.PacketInAdminCollectable;
import de.tr7zw.javaorbit.server.connection.packet.play.in.admin.PacketInAdminCompany;
import de.tr7zw.javaorbit.server.connection.packet.play.in.admin.PacketInAdminName;
import de.tr7zw.javaorbit.server.connection.packet.play.in.admin.PacketInAdminPos;
import de.tr7zw.javaorbit.server.connection.packet.play.in.admin.PacketInAdminRank;
import de.tr7zw.javaorbit.server.connection.packet.play.in.admin.PacketInAdminShip;
import de.tr7zw.javaorbit.server.connection.packet.play.in.admin.PacketInAdminTP;
import de.tr7zw.javaorbit.server.connection.packet.play.in.admin.PacketInAdminWarp;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PacketType {
	UNKNOWN(Direction.IN, "UNKNOWN", null),
	
	//IN
/*LOGIN(Direction.IN, "LOGIN|", PacketPlayInLogin.class),
PING(Direction.IN, "PNG", PacketPlayInPing.class),
JUMP(Direction.IN, "j", PacketPlayInJump.class),
MOVE(Direction.IN, "1|", PacketPlayInMove.class),
READY(Direction.IN, "RDY|", PacketPlayInReady.class),
SETTING(Direction.IN, "7|", PacketPlayInClientSetting.class),
ATTACK_LASER(Direction.IN, "a|", PacketPlayInLaserAttack.class),
STOP_LASER(Direction.IN, "G|", PacketPlayInLaserStop.class),
SELECT_AMMO(Direction.IN , "u|", PacketPlayInSelectAmmo.class),
SELECT_ROCKET(Direction.IN , "d|", PacketPlayInSelectRocket.class),
ATTACK_ROCKET(Direction.IN, "v|", PacketPlayInAttackRocket.class),
CHANGE_CONFIG(Direction.IN, "CFG|", PacketPlayInChangeConfig.class),
LOGOUT(Direction.IN, "l|", PacketPlayInLogout.class),
LOGOUT_CANCEL(Direction.IN, "o|", PacketPlayInLogoutCancel.class),
BUY(Direction.IN, "5|", PacketPlayInBuy.class),
SELECT_SHIP(Direction.IN, "SEL|", PacketPlayInSelectShip.class),
SELECT_SHIP_RETRO(Direction.IN, "L|", PacketPlayInSelectShipRetro.class),
SELECT(Direction.IN, "S|", PacketPlayInSelect.class),
TECH(Direction.IN, "TX|", PacketPlayInTech.class),*/

//IN Admin
ADMIN_RANK(Direction.IN, "/RANK", PacketInAdminRank.class),
ADMIN_COMPANY(Direction.IN, "/COMP", PacketInAdminCompany.class),
ADMIN_CLAN(Direction.IN, "/CLAN", PacketInAdminClan.class),
ADMIN_WARP(Direction.IN, "/WARP", PacketInAdminWarp.class),
ADMIN_SHIP(Direction.IN, "/SHIP", PacketInAdminShip.class),
ADMIN_TP(Direction.IN, "/TP", PacketInAdminTP.class),
ADMIN_SPAWN_COLLECTABLE(Direction.IN, "/BOX", PacketInAdminCollectable.class),
ADMIN_SPAWN_BOMB(Direction.IN, "/BOMB", PacketInAdminBomb.class),
ADMIN_POS(Direction.IN, "/POS", PacketInAdminPos.class),
ADMIN_NAME(Direction.IN, "/NAME", PacketInAdminName.class),

//Chat
CHAT_INIT(Direction.IN, "bu%", PacketChatInInit.class),
CHAT_CHATTING(Direction.IN, "a%", PacketChatInChatting.class),
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
