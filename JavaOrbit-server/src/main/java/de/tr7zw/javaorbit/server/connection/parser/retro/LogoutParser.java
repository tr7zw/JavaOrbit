package de.tr7zw.javaorbit.server.connection.parser.retro;

import de.tr7zw.javaorbit.server.connection.gamepackets.in.LogoutRequest;
import de.tr7zw.javaorbit.server.connection.parser.LegacyParser;

public class LogoutParser implements LegacyParser<LogoutRequest> {

    @Override
    public String packetId() {
        return "l|";
    }

    @Override
    public LogoutRequest parsePacket(String data) {
        // TODO Auto-generated method stub
        return null;
    }

}
