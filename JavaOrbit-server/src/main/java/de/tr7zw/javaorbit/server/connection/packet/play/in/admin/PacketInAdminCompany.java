package de.tr7zw.javaorbit.server.connection.packet.play.in.admin;

import de.tr7zw.javaorbit.server.connection.packet.PacketPlayIn;
import de.tr7zw.javaorbit.server.enums.Faction;
import de.tr7zw.javaorbit.server.enums.Rank;
import de.tr7zw.javaorbit.server.player.Player;

public class PacketInAdminCompany extends PacketPlayIn{

	String comp;
	
	public PacketInAdminCompany(String data) {
		super(data);
		comp = data.replace("" + (char)0x00, "").replace("\n", "").replace("\r", "").split(" ")[1];
	}

	@Override
	public void onRecieve(Player player) {
		try {
			player.changeFaction(Faction.valueOf(comp.toUpperCase()));
			player.refreshPlayer();
		}catch(Exception ex) {
			player.sendMessage("Company not found!");
		}
	}

}
