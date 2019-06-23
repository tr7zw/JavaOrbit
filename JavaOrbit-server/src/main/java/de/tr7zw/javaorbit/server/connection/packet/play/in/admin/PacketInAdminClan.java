package de.tr7zw.javaorbit.server.connection.packet.play.in.admin;

import de.tr7zw.javaorbit.server.connection.packet.PacketPlayIn;
import de.tr7zw.javaorbit.server.player.Clan;
import de.tr7zw.javaorbit.server.player.Player;

public class PacketInAdminClan extends PacketPlayIn{

	String clan;
	
	public PacketInAdminClan(String data) {
		super(data);
		clan = data.replace("" + (char)0x00, "").replace("\n", "").replace("\r", "").split(" ")[1];
	}

	@Override
	public void onRecieve(Player player) {
		try {
			player.changeClan(new Clan(Clan.getCounteter().incrementAndGet(), clan, clan));
			player.refreshPlayer();
		}catch(Exception ex) {
			player.sendMessage("Error!");
		}
	}

}
