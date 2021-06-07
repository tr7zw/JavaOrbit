package de.tr7zw.javaorbit.server.connection.parser.retro;

import de.tr7zw.javaorbit.server.connection.gamepackets.in.LogoutCancelRequest;
import de.tr7zw.javaorbit.server.connection.parser.LegacyParser;

public class LogoutCancelParser implements LegacyParser<LogoutCancelRequest>{

    @Override
    public String packetId() {
        return "o|";
    }

    @Override
    public LogoutCancelRequest parsePacket(String data) {
        // TODO Auto-generated method stub
        return null;
    }

}
