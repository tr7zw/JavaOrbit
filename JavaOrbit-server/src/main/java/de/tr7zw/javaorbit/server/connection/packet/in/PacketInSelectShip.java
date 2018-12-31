package de.tr7zw.javaorbit.server.connection.packet.in;

import de.tr7zw.javaorbit.server.connection.packet.PacketIn;
import de.tr7zw.javaorbit.server.connection.packet.out.PacketOutShipData;
import de.tr7zw.javaorbit.server.maps.MapInstance;
import de.tr7zw.javaorbit.server.maps.entities.EntityLiving;
import de.tr7zw.javaorbit.server.player.Player;

public class PacketInSelectShip extends PacketIn{

	private int id;
	
	public PacketInSelectShip(String data) { // SEL|2
		super(data);
		id = Integer.parseInt(clearString(data).split("\\|")[1]);
	}

	@Override
	public void onRecieve(Player player) {
		MapInstance instance = player.getLocation().getInstance();
		EntityLiving target = instance.getEntity(id);
		if(target == null)return;
		if(target == player)return;
		//TODO: Check range
		
		player.getPlayerView().setSelectedId(target.getId());
		player.getPlayerView().setSelected(target);
		player.sendPacket(new PacketOutShipData(target.getId(), target.getName(), target.getShip().getShield(), target.getShip().getMaxShield(), target.getShip().getHp(), target.getShip().getMaxHp()));
	}

}
