package de.tr7zw.javaorbit.server.connection.packet.play.in;

import de.tr7zw.javaorbit.server.connection.packet.PacketPlayIn;
import de.tr7zw.javaorbit.server.player.Player;

public class PacketPlayInJump extends PacketPlayIn{

	public PacketPlayInJump(String data) {
		super(data);
		//It's just "J"
	}

	@Override
	public void onRecieve(Player player) {
		
	}

}
