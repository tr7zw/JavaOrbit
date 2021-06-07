package de.tr7zw.javaorbit.server.connection.parser.retro;

import de.tr7zw.javaorbit.server.connection.gamepackets.in.ReadyRequest;
import de.tr7zw.javaorbit.server.connection.parser.LegacyParser;

public class ReadyParser implements LegacyParser<ReadyRequest>{

    @Override
    public String packetId() {
        return "RDY";
    }

    @Override
    public ReadyRequest parsePacket(String data) {
        return new ReadyRequest((short)-1, (short)-1, (short)-1);
    }

}
