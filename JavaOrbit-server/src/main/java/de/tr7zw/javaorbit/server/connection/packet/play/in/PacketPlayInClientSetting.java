package de.tr7zw.javaorbit.server.connection.packet.play.in;

import de.tr7zw.javaorbit.server.connection.packet.PacketPlayIn;
import de.tr7zw.javaorbit.server.player.Player;

public class PacketPlayInClientSetting extends PacketPlayIn{

	public PacketPlayInClientSetting(String data) {
		super(data);
		// 7|QUALITY_EXPLOSION|3
	}

	@Override
	public void onRecieve(Player player) {
		// TODO Auto-generated method stub
		
	}

}
