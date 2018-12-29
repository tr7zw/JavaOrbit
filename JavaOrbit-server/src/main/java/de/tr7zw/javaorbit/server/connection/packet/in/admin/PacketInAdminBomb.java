package de.tr7zw.javaorbit.server.connection.packet.in.admin;

import de.tr7zw.javaorbit.server.connection.packet.PacketIn;
import de.tr7zw.javaorbit.server.connection.packet.out.PacketOutSpawnMine;
import de.tr7zw.javaorbit.server.enums.collectables.Mine;
import de.tr7zw.javaorbit.server.player.Player;

public class PacketInAdminBomb extends PacketIn{

	
	public PacketInAdminBomb(String data) {
		super(data);
	}

	private static int counter = -1;
	
	@Override
	public void onRecieve(Player player) {
		player.sendPacket(new PacketOutSpawnMine(--counter, Mine.PIRATE, (int)player.getLocation().getX(), (int)player.getLocation().getY()));
	}
}
