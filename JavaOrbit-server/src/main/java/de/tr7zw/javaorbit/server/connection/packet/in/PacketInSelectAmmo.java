package de.tr7zw.javaorbit.server.connection.packet.in;

import de.tr7zw.javaorbit.server.connection.packet.PacketIn;
import de.tr7zw.javaorbit.server.player.Player;

public class PacketInSelectAmmo extends PacketIn{

	public PacketInSelectAmmo(String data) {
		super(data);
		// u|5
	}

	@Override
	public void onRecieve(Player player) {
		// TODO Auto-generated method stub
		
	}

}
