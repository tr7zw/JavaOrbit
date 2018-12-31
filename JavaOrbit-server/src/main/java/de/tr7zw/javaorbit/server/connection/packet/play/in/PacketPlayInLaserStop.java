package de.tr7zw.javaorbit.server.connection.packet.play.in;

import de.tr7zw.javaorbit.server.connection.packet.PacketPlayIn;
import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutLaserStop;
import de.tr7zw.javaorbit.server.player.Player;

public class PacketPlayInLaserStop extends PacketPlayIn{

	private int targetId;
	
	public PacketPlayInLaserStop(String data) {
		super(data);
		targetId = Integer.parseInt(clearString(data).split("\\|")[1]);
	}

	@Override
	public void onRecieve(Player player) {
		player.getPlayerView().setAttacking(false);
	}

}
