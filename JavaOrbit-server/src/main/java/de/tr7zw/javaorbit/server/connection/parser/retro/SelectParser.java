package de.tr7zw.javaorbit.server.connection.parser.retro;

import de.tr7zw.javaorbit.server.connection.gamepackets.in.SelectRequest;
import de.tr7zw.javaorbit.server.connection.parser.LegacyParser;

public class SelectParser implements LegacyParser<SelectRequest>{

    @Override
    public String packetId() {
        return "S|";
    }

    @Override
    public SelectRequest parsePacket(String data) {
        // TODO Auto-generated method stub
        return null;
    }

}
