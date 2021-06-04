package de.tr7zw.javaorbit.server.connection.packet.play.in.admin;

import de.tr7zw.javaorbit.server.connection.packet.PacketPlayIn;
import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutSpawnCollectable;
import de.tr7zw.javaorbit.server.enums.collectables.Collectable;
import de.tr7zw.javaorbit.server.player.Player;

public class PacketInAdminCollectable extends PacketPlayIn{

	
	public PacketInAdminCollectable(String data) {
		super(data);
	}

	private static int counter = -1;
	
	@Override
	public void onRecieve(Player player) {
		player.sendPacket(new PacketPlayOutSpawnCollectable(--counter, Collectable.TURKEY, player.getPositionComponent().x, player.getPositionComponent().y));
	}
}
