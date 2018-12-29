package de.tr7zw.javaorbit.server.connection.packet.in.admin;

import de.tr7zw.javaorbit.server.connection.packet.PacketIn;
import de.tr7zw.javaorbit.server.enums.Rank;
import de.tr7zw.javaorbit.server.player.Player;

public class PacketInAdminRank extends PacketIn{

	String rank;
	
	public PacketInAdminRank(String data) {
		super(data);
		rank = data.replace("" + (char)0x00, "").replace("\n", "").replace("\r", "").split(" ")[1];
	}

	@Override
	public void onRecieve(Player player) {
		try {
			player.changeRank(Rank.valueOf(rank.toUpperCase()));
			player.refreshPlayer();
		}catch(Exception ex) {
			player.sendMessage("Rank not found!");
		}
	}

}
