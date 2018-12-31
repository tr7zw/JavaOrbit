package de.tr7zw.javaorbit.server.connection.packet.play.in;

import de.tr7zw.javaorbit.server.connection.packet.PacketPlayIn;
import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutShootLaser;
import de.tr7zw.javaorbit.server.enums.Ammo;
import de.tr7zw.javaorbit.server.player.Player;

public class PacketPlayInLaserAttack extends PacketPlayIn{

	private int targetId;
	
	public PacketPlayInLaserAttack(String data) {
		super(data);
		targetId = Integer.parseInt(clearString(data).split("\\|")[1]);
	}

	@Override
	public void onRecieve(Player player) {
		if(targetId == player.getPlayerView().getSelectedId() && player.getPlayerView().getSelected() != null) {
			player.getLocation().getInstance().sendContextPacket(player, new PacketPlayOutShootLaser(player.getId(), targetId, Ammo.USB100, true, true));
			player.getPlayerView().setAttacking(true);
		}
	}

}
