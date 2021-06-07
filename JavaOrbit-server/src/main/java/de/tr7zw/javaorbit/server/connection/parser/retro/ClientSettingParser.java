package de.tr7zw.javaorbit.server.connection.parser.retro;

import de.tr7zw.javaorbit.server.connection.gamepackets.in.ClientSettingRequest;
import de.tr7zw.javaorbit.server.connection.parser.LegacyParser;

public class ClientSettingParser implements LegacyParser<ClientSettingRequest>{

    @Override
    public String packetId() {
        return "7|";
    }

    @Override
    public ClientSettingRequest parsePacket(String data) {
        // TODO Auto-generated method stub
        // 7|QUALITY_EXPLOSION|3
        return null;
    }

}
