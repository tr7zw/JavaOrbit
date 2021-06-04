package de.tr7zw.javaorbit.server.connection.packet.play.in;

import de.tr7zw.javaorbit.server.components.GateUserComponent;
import de.tr7zw.javaorbit.server.connection.packet.PacketPlayIn;
import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutActivateGate;
import de.tr7zw.javaorbit.server.player.Player;

public class PacketPlayInJump extends PacketPlayIn{

	public PacketPlayInJump(String data) {
		super(data);
		//It's just "J"
	}

	@Override
	public void onRecieve(Player player) {
		GateUserComponent user = player.getGateUserComponent();
		if(user.gateInfo == null) {
			player.sendMessage("You're not near a jump gate!");
		}else{
		    if(user.usingGate == null) {
		        user.usingGate = user.gateInfo;
		        user.gateStartTime = System.currentTimeMillis();
    			player.sendPacket(new PacketPlayOutActivateGate(player.getPositionComponent().instance.getMap().getId(), user.gateId.id));
		    }
		}
	}

}
