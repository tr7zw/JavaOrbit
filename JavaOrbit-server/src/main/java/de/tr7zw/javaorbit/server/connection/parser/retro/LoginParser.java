package de.tr7zw.javaorbit.server.connection.parser.retro;

import de.tr7zw.javaorbit.server.connection.gamepackets.in.LoginRequest;
import de.tr7zw.javaorbit.server.connection.parser.LegacyParser;

public class LoginParser implements LegacyParser<LoginRequest>{

    @Override
    public String packetId() {
        return "LOGIN|";
    }

    @Override
    public LoginRequest parsePacket(String data) {
        String[] ar = data.split("\\|");
        return new LoginRequest(-1, Integer.parseInt(ar[1]), -1, ar[2], ar.length >= 4 ? ar[3] : null);
    }

}
