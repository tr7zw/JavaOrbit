package de.tr7zw.javaorbit.server.connection.parser.retro;

import de.tr7zw.javaorbit.server.connection.gamepackets.in.SelectRocketRequest;
import de.tr7zw.javaorbit.server.connection.parser.LegacyParser;

public class SelectRocketParser implements LegacyParser<SelectRocketRequest>{

    @Override
    public String packetId() {
        return "d|";
    }

    // d|1
    @Override
    public SelectRocketRequest parsePacket(String data) {
        // TODO Auto-generated method stub
        return null;
    }

}
