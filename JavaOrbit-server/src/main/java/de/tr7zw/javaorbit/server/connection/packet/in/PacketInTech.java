package de.tr7zw.javaorbit.server.connection.packet.in;

import de.tr7zw.javaorbit.server.connection.packet.PacketIn;
import de.tr7zw.javaorbit.server.player.Player;

public class PacketInTech extends PacketIn{

	public PacketInTech(String data) {
		super(data);
		// TX|1
	}

	@Override
	public void onRecieve(Player player) {
		// TODO Auto-generated method stub
		
	}

}
