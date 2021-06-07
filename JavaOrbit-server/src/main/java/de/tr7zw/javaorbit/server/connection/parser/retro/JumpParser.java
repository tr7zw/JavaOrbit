package de.tr7zw.javaorbit.server.connection.parser.retro;

import de.tr7zw.javaorbit.server.connection.gamepackets.in.JumpRequest;
import de.tr7zw.javaorbit.server.connection.parser.LegacyParser;

public class JumpParser implements LegacyParser<JumpRequest>{

    @Override
    public String packetId() {
        return "j";
    }

    @Override
    public JumpRequest parsePacket(String data) {
        return new JumpRequest();
    }

}
