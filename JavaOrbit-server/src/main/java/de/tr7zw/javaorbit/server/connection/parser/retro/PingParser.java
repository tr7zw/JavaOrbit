package de.tr7zw.javaorbit.server.connection.parser.retro;

import de.tr7zw.javaorbit.server.connection.gamepackets.in.PingRequest;
import de.tr7zw.javaorbit.server.connection.parser.LegacyParser;

public class PingParser implements LegacyParser<PingRequest>{

    @Override
    public String packetId() {
        return "PNG";
    }

    @Override
    public PingRequest parsePacket(String data) {
         return new PingRequest();
    }

}
