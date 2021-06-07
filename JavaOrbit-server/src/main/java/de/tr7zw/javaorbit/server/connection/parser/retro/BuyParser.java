package de.tr7zw.javaorbit.server.connection.parser.retro;

import de.tr7zw.javaorbit.server.connection.gamepackets.in.BuyRequest;
import de.tr7zw.javaorbit.server.connection.parser.LegacyParser;

public class BuyParser implements LegacyParser<BuyRequest> {

    @Override
    public String packetId() {
        return "5|";
    }

    @Override
    public BuyRequest parsePacket(String data) {
        // TODO Auto-generated method stub
        return null;
    }

}
