package de.tr7zw.javaorbit.server.connection.packet.in.admin;

import de.tr7zw.javaorbit.server.connection.packet.PacketIn;
import de.tr7zw.javaorbit.server.npc.EntityUfo;
import de.tr7zw.javaorbit.server.player.Player;

public class PacketInAdminSpawn extends PacketIn{

	public PacketInAdminSpawn(String data) {
		super(data);
	}

	//private static int counter = -1;
	
	@Override
	public void onRecieve(Player player) {
		//player.sendPacket(new PacketOutSpawnShip(--counter, ShipType.UFO_BIG, "-=[ UFO ]=-", (int)player.getLocation().getX(), (int)player.getLocation().getY(), Faction.EIC, true, Rank.ADMIN, Rings.PYRAMIDE));
		//player.sendPacket(new PacketOutPermanentTitle(counter, Title.ADMIN_TITLE));
		player.getLocation().getInstance().addNPC(new EntityUfo(player.getLocation().clone()));
	}

}
