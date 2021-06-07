package de.tr7zw.javaorbit.server.connection.parser.retro;

import de.tr7zw.javaorbit.server.connection.gamepackets.in.LaserAttackRequest;
import de.tr7zw.javaorbit.server.connection.parser.LegacyParser;

public class LaserAttackParser implements LegacyParser<LaserAttackRequest> {

    @Override
    public String packetId() {
        return "a|";
    }

    @Override
    public LaserAttackRequest parsePacket(String data) {
        return new LaserAttackRequest(Integer.parseInt(clearString(data).split("\\|")[1]));
    }

}
