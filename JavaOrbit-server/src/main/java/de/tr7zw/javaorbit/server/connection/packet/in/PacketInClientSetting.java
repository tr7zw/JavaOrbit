package de.tr7zw.javaorbit.server.connection.packet.in;

import de.tr7zw.javaorbit.server.connection.packet.PacketIn;
import de.tr7zw.javaorbit.server.player.Player;

public class PacketInClientSetting extends PacketIn{

	public PacketInClientSetting(String data) {
		super(data);
		// 7|QUALITY_EXPLOSION|3
	}

	@Override
	public void onRecieve(Player player) {
		// TODO Auto-generated method stub
		
	}

}
