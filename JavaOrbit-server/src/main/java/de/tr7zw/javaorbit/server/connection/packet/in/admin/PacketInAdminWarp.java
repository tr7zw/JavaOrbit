package de.tr7zw.javaorbit.server.connection.packet.in.admin;

import de.tr7zw.javaorbit.server.Server;
import de.tr7zw.javaorbit.server.connection.packet.PacketIn;
import de.tr7zw.javaorbit.server.enums.Maps;
import de.tr7zw.javaorbit.server.player.Player;

public class PacketInAdminWarp extends PacketIn{

	String target;

	public PacketInAdminWarp(String data) {
		super(data);
		target = data.replace("" + (char)0x00, "").replace("\n", "").replace("\r", "").replace("-", "_");
	}

	@Override
	public void onRecieve(Player player) {
		Maps map = null;
		try {
			map = Maps.valueOf("MAP" + target.split(" ")[1]);
		}catch(Exception ex) {}
		if(map == null) {
			try {
				map = Maps.valueOf("GATE_" + target.split(" ")[1].toUpperCase());
			}catch(Exception ex) {}
		}
		if(map == null) {
			try {
				map = Maps.valueOf(target.split(" ")[1].toUpperCase());
			}catch(Exception ex) {}
		}
		if(map == null) {
			player.sendMessage("Map not found!");
			return;
		}
		Server.getInstance().getMapManager().enterMap(player, map, 1000, 1000);
	}

}
