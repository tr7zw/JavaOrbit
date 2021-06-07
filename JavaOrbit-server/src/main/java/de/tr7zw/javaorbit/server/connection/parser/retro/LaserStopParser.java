package de.tr7zw.javaorbit.server.connection.parser.retro;

import de.tr7zw.javaorbit.server.connection.gamepackets.in.LaserStopRequest;
import de.tr7zw.javaorbit.server.connection.parser.LegacyParser;

public class LaserStopParser implements LegacyParser<LaserStopRequest>{

    @Override
    public String packetId() {
        return "G|";
    }

    @Override
    public LaserStopRequest parsePacket(String data) {
        return new LaserStopRequest(Integer.parseInt(clearString(data).split("\\|")[1]));
    }

}
