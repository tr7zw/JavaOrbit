package de.tr7zw.javaorbit.server.connection.packet.play.in.admin;

import de.tr7zw.javaorbit.server.connection.packet.PacketPlayIn;
import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutSpawnMine;
import de.tr7zw.javaorbit.server.enums.collectables.Mine;
import de.tr7zw.javaorbit.server.player.Player;

public class PacketInAdminBomb extends PacketPlayIn{

	
	public PacketInAdminBomb(String data) {
		super(data);
	}

	private static int counter = -1;
	
	@Override
	public void onRecieve(Player player) {
		player.sendPacket(new PacketPlayOutSpawnMine(--counter, Mine.PIRATE, player.getPositionComponent().x, player.getPositionComponent().y));
	}
}
