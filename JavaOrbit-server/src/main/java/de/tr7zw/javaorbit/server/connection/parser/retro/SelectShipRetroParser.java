package de.tr7zw.javaorbit.server.connection.parser.retro;

import de.tr7zw.javaorbit.server.connection.gamepackets.in.SelectShipRequest;
import de.tr7zw.javaorbit.server.connection.parser.LegacyParser;

public class SelectShipRetroParser implements LegacyParser<SelectShipRequest>{

    @Override
    public String packetId() {
        return "SEL|";
    }

    @Override
    public SelectShipRequest parsePacket(String data) {
        return new SelectShipRequest(Integer.parseInt(clearString(data).split("\\|")[1]));
    }

}
