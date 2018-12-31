package de.tr7zw.javaorbit.server.connection.packet.in;

import de.tr7zw.javaorbit.server.connection.packet.PacketIn;
import de.tr7zw.javaorbit.server.connection.packet.out.PacketOutShootLaser;
import de.tr7zw.javaorbit.server.enums.Ammo;
import de.tr7zw.javaorbit.server.player.Player;

public class PacketInLaserAttack extends PacketIn{

	private int targetId;
	
	public PacketInLaserAttack(String data) {
		super(data);
		targetId = Integer.parseInt(clearString(data).split("\\|")[1]);
	}

	@Override
	public void onRecieve(Player player) {
		if(targetId == player.getPlayerView().getSelectedId() && player.getPlayerView().getSelected() != null) {
			player.getLocation().getInstance().sendContextPacket(player, new PacketOutShootLaser(player.getId(), targetId, Ammo.USB100, true, true));
			player.getPlayerView().setAttacking(true);
		}
	}

}
