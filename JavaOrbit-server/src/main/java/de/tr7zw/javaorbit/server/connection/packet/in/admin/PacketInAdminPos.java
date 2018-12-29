package de.tr7zw.javaorbit.server.connection.packet.in.admin;

import de.tr7zw.javaorbit.server.connection.packet.PacketIn;
import de.tr7zw.javaorbit.server.player.Player;

public class PacketInAdminPos extends PacketIn{

	public PacketInAdminPos(String data) {
		super(data);
	}

	@Override
	public void onRecieve(Player player) {
		player.sendMessage(player.getLocation().getX() + ";" + player.getLocation().getY());
	}

}
