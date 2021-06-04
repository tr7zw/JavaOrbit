package de.tr7zw.javaorbit.server.connection.packet.play.in;

import de.tr7zw.javaorbit.server.connection.packet.PacketPlayIn;
import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutShipData;
import de.tr7zw.javaorbit.server.maps.MapInstance;
import de.tr7zw.javaorbit.server.maps.entities.EntityLiving;
import de.tr7zw.javaorbit.server.player.Player;

public class PacketPlayInSelectShip extends PacketPlayIn{

	private int id;
	
	public PacketPlayInSelectShip(String data) { // SEL|2
		super(data);
		id = Integer.parseInt(clearString(data).split("\\|")[1]);
	}

	@Override
	public void onRecieve(Player player) {
		MapInstance instance = player.getPositionComponent().instance;
		EntityLiving target = instance.getEntity(id);
		if(target == null)return;
		if(target == player)return;
		//TODO: Check range
		
		player.getPlayerView().setSelectedId(target.getId());
		player.getPlayerView().setSelected(target);
		player.sendPacket(new PacketPlayOutShipData(target.getId(), target.getName(),  target.getShip().getHp(), target.getShip().getMaxHp(), target.getShip().getShield(), target.getShip().getMaxShield()));
		player.getPlayerView().setAttacking(false);
	}

}
