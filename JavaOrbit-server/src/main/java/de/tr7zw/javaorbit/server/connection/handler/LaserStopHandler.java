package de.tr7zw.javaorbit.server.connection.handler;

import de.tr7zw.javaorbit.server.connection.PacketHandler;
import de.tr7zw.javaorbit.server.connection.PlayerConnection;
import de.tr7zw.javaorbit.server.connection.gamepackets.in.LaserStopRequest;

public class LaserStopHandler implements PacketHandler<LaserStopRequest>{

    @Override
    public void process(PlayerConnection connection, LaserStopRequest packet) {
        connection.getPlayer().getPlayerView().setAttacking(false);
    }

}
