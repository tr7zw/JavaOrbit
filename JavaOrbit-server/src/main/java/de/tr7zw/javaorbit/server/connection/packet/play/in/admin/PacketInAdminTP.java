package de.tr7zw.javaorbit.server.connection.packet.play.in.admin;

import de.tr7zw.javaorbit.server.connection.packet.PacketPlayIn;
import de.tr7zw.javaorbit.server.player.Player;

public class PacketInAdminTP extends PacketPlayIn{
	
	public PacketInAdminTP(String data) {
		super(data);
	}

	@Override
	public void onRecieve(Player player) {
		try {
			player.setLocation(player.getTargetLocation());
			player.refreshPlayer();
		}catch(Exception ex) {
			player.sendMessage("Rank not found!");
		}
	}

}
