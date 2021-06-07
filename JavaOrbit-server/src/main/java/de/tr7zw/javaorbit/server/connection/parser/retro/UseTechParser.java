package de.tr7zw.javaorbit.server.connection.parser.retro;

import de.tr7zw.javaorbit.server.connection.gamepackets.in.UseTechRequest;
import de.tr7zw.javaorbit.server.connection.parser.LegacyParser;

public class UseTechParser implements LegacyParser<UseTechRequest>{

    @Override
    public String packetId() {
        return "TX|";
    }

    @Override
    public UseTechRequest parsePacket(String data) {
        // TODO Auto-generated method stub
        return null;
    }

}
