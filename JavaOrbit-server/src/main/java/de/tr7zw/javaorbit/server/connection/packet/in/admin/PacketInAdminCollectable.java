package de.tr7zw.javaorbit.server.connection.packet.in.admin;

import de.tr7zw.javaorbit.server.connection.packet.PacketIn;
import de.tr7zw.javaorbit.server.connection.packet.out.PacketOutSpawnCollectable;
import de.tr7zw.javaorbit.server.enums.collectables.Collectable;
import de.tr7zw.javaorbit.server.player.Player;

public class PacketInAdminCollectable extends PacketIn{

	
	public PacketInAdminCollectable(String data) {
		super(data);
	}

	private static int counter = -1;
	
	@Override
	public void onRecieve(Player player) {
		player.sendPacket(new PacketOutSpawnCollectable(--counter, Collectable.TURKEY, (int)player.getLocation().getX(), (int)player.getLocation().getY()));
	}
}
