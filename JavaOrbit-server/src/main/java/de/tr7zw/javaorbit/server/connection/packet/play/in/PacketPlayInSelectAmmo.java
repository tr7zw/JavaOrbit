package de.tr7zw.javaorbit.server.connection.packet.play.in;

import de.tr7zw.javaorbit.server.connection.packet.PacketPlayIn;
import de.tr7zw.javaorbit.server.enums.Ammo;
import de.tr7zw.javaorbit.server.player.Player;

public class PacketPlayInSelectAmmo extends PacketPlayIn{

private int ammoType;

	public PacketPlayInSelectAmmo(String data) {
		super(data);
		// u|5
		ammoType = Integer.parseInt(clearString(data).split("\\|")[1]);
	}

	@Override
	public void onRecieve(Player player) {
		player.setAmmo(Ammo.fromId(ammoType-1));
	}

}
