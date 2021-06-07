package de.tr7zw.javaorbit.server.connection.parser.retro;

import de.tr7zw.javaorbit.server.connection.gamepackets.in.SelectAmmoRequest;
import de.tr7zw.javaorbit.server.connection.parser.LegacyParser;

public class SelectAmmoParser implements LegacyParser<SelectAmmoRequest> {

    @Override
    public String packetId() {
        return "u|";
    }

    @Override
    public SelectAmmoRequest parsePacket(String data) {
        return new SelectAmmoRequest(Integer.parseInt(clearString(data).split("\\|")[1]));
    }

}
