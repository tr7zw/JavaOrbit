package de.tr7zw.javaorbit.server.connection.handler;

import de.tr7zw.javaorbit.server.connection.PacketHandler;
import de.tr7zw.javaorbit.server.connection.PlayerConnection;
import de.tr7zw.javaorbit.server.connection.gamepackets.in.MoveRequest;

public class MoveHandler implements PacketHandler<MoveRequest> {

    @Override
    public void process(PlayerConnection connection, MoveRequest packet) {
        connection.getPlayer().getPositionComponent().instance.getMovementSystem().changeMoveTarget(
                connection.getPlayer().getEntity(), packet.getTargetPosX(), packet.getTargetPosY(), packet.getPosX(),
                packet.getPosY(), connection.getPlayer().getShip().getSpeed());
    }

}
