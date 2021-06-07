package de.tr7zw.javaorbit.server.connection.parser.retro;

import de.tr7zw.javaorbit.server.connection.gamepackets.in.ChangeConfigRequest;
import de.tr7zw.javaorbit.server.connection.parser.LegacyParser;

public class ChangeConfigParser implements LegacyParser<ChangeConfigRequest> {

    @Override
    public String packetId() {
        return "CFG|";
    }

    @Override
    public ChangeConfigRequest parsePacket(String data) {
        // TODO Auto-generated method stub
        return null;
    }

}
