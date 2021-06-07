package de.tr7zw.javaorbit.server.connection.parser;

import de.tr7zw.javaorbit.server.connection.packet.Packet;

public interface LegacyParser<T extends Packet> {

    public String packetId();
    public T parsePacket(String data);
    
    public default String clearString(String data) {
        return data.replace("" + (char)0x00, "").replace("\n", "").replace("\r", "");
    }
    
}
