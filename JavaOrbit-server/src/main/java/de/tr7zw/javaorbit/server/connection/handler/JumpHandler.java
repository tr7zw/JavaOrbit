package de.tr7zw.javaorbit.server.connection.handler;

import de.tr7zw.javaorbit.server.components.GateUserComponent;
import de.tr7zw.javaorbit.server.connection.PacketHandler;
import de.tr7zw.javaorbit.server.connection.PlayerConnection;
import de.tr7zw.javaorbit.server.connection.gamepackets.in.JumpRequest;
import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutActivateGate;

public class JumpHandler implements PacketHandler<JumpRequest>{

    @Override
    public void process(PlayerConnection connection, JumpRequest packet) {
        GateUserComponent user = connection.getPlayer().getGateUserComponent();
        if(user.gateInfo == null) {
            connection.getPlayer().sendMessage("You're not near a jump gate!");
        }else{
            if(user.usingGate == null) {
                user.usingGate = user.gateInfo;
                user.gateStartTime = System.currentTimeMillis();
                connection.getPlayer().sendPacket(new PacketPlayOutActivateGate(connection.getPlayer().getPositionComponent().instance.getMap().getId(), user.gateId.id));
            }
        }
    }

}
