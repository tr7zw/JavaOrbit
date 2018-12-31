package de.tr7zw.javaorbit.server.connection.packet.play.in;

import de.tr7zw.javaorbit.server.connection.packet.PacketPlayIn;
import de.tr7zw.javaorbit.server.player.Player;

public class PacketPlayInBuy extends PacketPlayIn{

	public PacketPlayInBuy(String data) {
		super(data);
		// 5|b|1|1000
		// 5|r|1|100
	}

	@Override
	public void onRecieve(Player player) {
		// TODO Auto-generated method stub
		
	}

}
