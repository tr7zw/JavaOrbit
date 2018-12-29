package de.tr7zw.javaorbit.server.connection.packet.in;

import de.tr7zw.javaorbit.server.connection.packet.PacketIn;
import de.tr7zw.javaorbit.server.player.Player;

public class PacketInBuy extends PacketIn{

	public PacketInBuy(String data) {
		super(data);
		// 5|b|1|1000
		// 5|r|1|100
	}

	@Override
	public void onRecieve(Player player) {
		// TODO Auto-generated method stub
		
	}

}
