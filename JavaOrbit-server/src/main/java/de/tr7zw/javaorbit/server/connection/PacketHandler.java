package de.tr7zw.javaorbit.server.connection;

import de.tr7zw.javaorbit.server.connection.packet.Packet;

public interface PacketHandler<T extends Packet> {

    public void process(PlayerConnection connection, T packet);
    
}
