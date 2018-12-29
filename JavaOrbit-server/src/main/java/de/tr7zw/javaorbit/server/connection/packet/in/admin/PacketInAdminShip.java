package de.tr7zw.javaorbit.server.connection.packet.in.admin;

import de.tr7zw.javaorbit.server.connection.packet.PacketIn;
import de.tr7zw.javaorbit.server.enums.ShipType;
import de.tr7zw.javaorbit.server.player.Player;

public class PacketInAdminShip extends PacketIn{

	String shipName;
	
	public PacketInAdminShip(String data) {
		super(data);
		shipName = data.replace("" + (char)0x00, "").replace("\n", "").replace("\r", "").split(" ")[1];
	}

	@Override
	public void onRecieve(Player player) {
		try {
			player.changeShip(ShipType.valueOf(shipName.toUpperCase()));
		}catch(Exception ex) {
			player.sendMessage("Ship not found!");
		}
	}

}
