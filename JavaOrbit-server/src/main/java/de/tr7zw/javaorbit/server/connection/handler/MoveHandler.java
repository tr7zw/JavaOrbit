package de.tr7zw.javaorbit.server.connection.handler;

import de.tr7zw.javaorbit.server.connection.PacketHandler;
import de.tr7zw.javaorbit.server.connection.PlayerConnection;
import de.tr7zw.javaorbit.server.connection.gamepackets.in.MoveRequest;
import de.tr7zw.javaorbit.server.player.Player;

public class MoveHandler implements PacketHandler<MoveRequest> {

    @Override
    public void process(PlayerConnection connection, MoveRequest packet) {
        Player player = connection.getPlayer();
        player.getPositionComponent().x = packet.getPosX();
        player.getPositionComponent().y = packet.getPosY();
        player.getMoveableComponent().x = packet.getTargetPosX();
        player.getMoveableComponent().y = packet.getTargetPosY();
        player.getMoveableComponent().moving = true;
        player.getMoveableComponent().startX = player.getPositionComponent().x;
        player.getMoveableComponent().startY = player.getPositionComponent().y;
        int distance = player.getMoveableComponent().distance();
        int time = distance / player.getShip().getSpeed() * 1080; //At least while testing 1080 was fitting the clients speed
        if(time == 0)time = 1000;
        player.getMoveableComponent().moveTime = time;
        player.getMoveableComponent().movingStartTime = System.currentTimeMillis();
    }

}
