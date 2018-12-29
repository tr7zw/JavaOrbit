package de.tr7zw.javaorbit.server.connection.packet.in;

import de.tr7zw.javaorbit.server.connection.packet.PacketIn;
import de.tr7zw.javaorbit.server.player.Player;

public class PacketInSelectRocket extends PacketIn{

	public PacketInSelectRocket(String data) {
		super(data);
		// d|1
	}

	@Override
	public void onRecieve(Player player) {
		// TODO Auto-generated method stub
		
	}

}
