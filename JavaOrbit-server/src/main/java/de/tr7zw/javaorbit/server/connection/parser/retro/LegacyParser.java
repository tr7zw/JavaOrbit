package de.tr7zw.javaorbit.server.connection.parser.retro;

import de.tr7zw.javaorbit.server.connection.packet.Packet;

public interface LegacyParser<T extends Packet> {

    public String packetId();
    public T parsePacket(String data);
    
}
