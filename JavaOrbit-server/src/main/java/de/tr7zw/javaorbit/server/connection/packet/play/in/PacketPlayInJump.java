package de.tr7zw.javaorbit.server.connection.packet.play.in;

import de.tr7zw.javaorbit.server.connection.packet.PacketPlayIn;
import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutActivateGate;
import de.tr7zw.javaorbit.server.maps.entities.EntityGate;
import de.tr7zw.javaorbit.server.player.Player;

public class PacketPlayInJump extends PacketPlayIn{

	public PacketPlayInJump(String data) {
		super(data);
		//It's just "J"
	}

	@Override
	public void onRecieve(Player player) {
		EntityGate gate = player.getNextGate();
		if(gate == null) {
			player.sendMessage("You're not near a jump gate!");
		}else{
			player.sendPacket(new PacketPlayOutActivateGate(gate.getLocation().getInstance().getMap().getId(), gate.getId()));
			player.setGateStartTime(System.currentTimeMillis());
			player.setUsingGate(gate);
		}
	}

}
