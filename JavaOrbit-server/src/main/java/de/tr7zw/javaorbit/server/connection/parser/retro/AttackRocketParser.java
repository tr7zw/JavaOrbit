package de.tr7zw.javaorbit.server.connection.parser.retro;

import de.tr7zw.javaorbit.server.connection.gamepackets.in.AttackRocketRequest;
import de.tr7zw.javaorbit.server.connection.parser.LegacyParser;

public class AttackRocketParser implements LegacyParser<AttackRocketRequest>{

    @Override
    public String packetId() {
        return "v|";
    }

    @Override
    public AttackRocketRequest parsePacket(String data) {
        // TODO Auto-generated method stub
        return null;
    }

}
