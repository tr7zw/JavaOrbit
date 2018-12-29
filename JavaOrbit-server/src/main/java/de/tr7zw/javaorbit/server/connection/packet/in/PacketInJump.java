package de.tr7zw.javaorbit.server.connection.packet.in;

import de.tr7zw.javaorbit.server.connection.packet.PacketIn;
import de.tr7zw.javaorbit.server.player.Player;

public class PacketInJump extends PacketIn{

	public PacketInJump(String data) {
		super(data);
		//It's just "J"
	}

	@Override
	public void onRecieve(Player player) {
		
	}

}
