package de.tr7zw.javaorbit.server.connection.parser.modern;

import java.io.DataInputStream;
import java.io.IOException;

import de.tr7zw.javaorbit.server.connection.packet.Packet;

public interface ModernParser<T extends Packet> {

    public short packetId();
    public T parsePacket(DataInputStream data) throws IOException;
    
}
