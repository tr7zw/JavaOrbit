package de.tr7zw.javaorbit.server.connection.packet.play.in.admin;

import de.tr7zw.javaorbit.server.connection.packet.PacketPlayIn;
import de.tr7zw.javaorbit.server.player.Player;

public class PacketInAdminPos extends PacketPlayIn{

	public PacketInAdminPos(String data) {
		super(data);
	}

	@Override
	public void onRecieve(Player player) {
		player.sendMessage(player.getPositionComponent().toString());
	}

}
