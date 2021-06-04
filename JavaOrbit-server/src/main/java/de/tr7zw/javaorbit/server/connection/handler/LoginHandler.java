package de.tr7zw.javaorbit.server.connection.handler;

import java.util.logging.Level;

import de.tr7zw.javaorbit.server.connection.PacketHandler;
import de.tr7zw.javaorbit.server.connection.PlayerConnection;
import de.tr7zw.javaorbit.server.connection.Session;
import de.tr7zw.javaorbit.server.connection.gamepackets.in.LoginRequest;
import de.tr7zw.javaorbit.server.enums.Version;
import de.tr7zw.javaorbit.server.player.Player;
import lombok.extern.java.Log;

@Log
public class LoginHandler implements PacketHandler<LoginRequest>{

    @Override
    public void process(PlayerConnection connection, LoginRequest packet) {
        connection.setPlayer(new Player(connection, new Session(packet.getUserID(), packet.getSessionID(), packet.getVersion()))); 
        connection.setVersion(Version.getVersion(packet.getVersion()));
        log.log(Level.INFO, "Player '" + connection.getPlayer().getName() + "' connected with version: " + connection.getVersion());
        //TODO: Validate session
        connection.getPlayer().sendLogin();
    }

}
