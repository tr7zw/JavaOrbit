package de.tr7zw.javaorbit.server.connection.parser.retro;

import de.tr7zw.javaorbit.server.connection.gamepackets.in.MoveRequest;
import de.tr7zw.javaorbit.server.connection.parser.LegacyParser;

public class MoveParser implements LegacyParser<MoveRequest>{

    @Override
    public String packetId() {
        return "1|";
    }

    //1|1984|2115|1978|1947|485
    //1|982|1105|1000|1000  2009 Client
    @Override
    public MoveRequest parsePacket(String data) {
        String[] ar = data.split("\\|");
        return new MoveRequest(Integer.parseInt(ar[1]), Integer.parseInt(ar[2]), Integer.parseInt(ar[3]), Integer.parseInt(ar[4]));
    }

}
