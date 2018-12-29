package de.tr7zw.javaorbit.server.connection.packet.in;

import de.tr7zw.javaorbit.server.connection.packet.PacketIn;
import de.tr7zw.javaorbit.server.player.Player;

public class PacketInReady extends PacketIn{

	public PacketInReady(String data) {
		super(data);
	}

	@Override
	public void onRecieve(Player player) {
		
	}

}
