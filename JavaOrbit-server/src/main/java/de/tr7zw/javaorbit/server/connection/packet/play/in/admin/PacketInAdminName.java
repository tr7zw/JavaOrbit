package de.tr7zw.javaorbit.server.connection.packet.play.in.admin;

import de.tr7zw.javaorbit.server.connection.packet.PacketPlayIn;
import de.tr7zw.javaorbit.server.enums.Faction;
import de.tr7zw.javaorbit.server.enums.Rank;
import de.tr7zw.javaorbit.server.player.Clan;
import de.tr7zw.javaorbit.server.player.Player;

public class PacketInAdminName extends PacketPlayIn{

	String name;
	
	public PacketInAdminName(String data) {
		super(data);
		name = data.replace("" + (char)0x00, "").replace("\n", "").replace("\r", "").split(" ")[1];
	}

	@Override
	public void onRecieve(Player player) {
		try {
			player.changeName(name);
			player.refreshPlayer();
		}catch(Exception ex) {
			player.sendMessage("Error!");
		}
	}

}
