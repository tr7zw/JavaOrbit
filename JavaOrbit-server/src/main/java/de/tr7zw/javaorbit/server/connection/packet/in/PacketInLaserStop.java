package de.tr7zw.javaorbit.server.connection.packet.in;

import de.tr7zw.javaorbit.server.connection.packet.PacketIn;
import de.tr7zw.javaorbit.server.connection.packet.out.PacketOutLaserStop;
import de.tr7zw.javaorbit.server.player.Player;

public class PacketInLaserStop extends PacketIn{

	private int targetId;
	
	public PacketInLaserStop(String data) {
		super(data);
		targetId = Integer.parseInt(clearString(data).split("\\|")[1]);
	}

	@Override
	public void onRecieve(Player player) {
		player.getPlayerView().setAttacking(false);
	}

}
