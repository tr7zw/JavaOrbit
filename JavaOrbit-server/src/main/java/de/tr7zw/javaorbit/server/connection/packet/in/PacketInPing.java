package de.tr7zw.javaorbit.server.connection.packet.in;

import de.tr7zw.javaorbit.server.connection.packet.PacketIn;
import de.tr7zw.javaorbit.server.player.Player;

public class PacketInPing extends PacketIn{

	public PacketInPing(String data) {
		super(data);
	}

	@Override
	public void onRecieve(Player player) {
		
	}

}
