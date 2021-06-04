package de.tr7zw.javaorbit.server.connection.packet.play.in;

import de.tr7zw.javaorbit.server.connection.packet.PacketPlayIn;
import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutLaserStop;
import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutShootLaser;
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
			if(player.getPlayerView().getSelected() instanceof Player && ((Player)player.getPlayerView().getSelected()).inDemilitarizedZone()){
				player.getConnection().send("0|P");
				player.sendPacket(new PacketPlayOutLaserStop(player.getId(), player.getPlayerView().getSelected().getId()));
			}else{
				player.getPositionComponent().instance.sendContextPacket(player, new PacketPlayOutShootLaser(player.getId(), targetId, player.getAmmo(), true, true));
				player.getPlayerView().setAttacking(true);
			}
		}
	}

}
