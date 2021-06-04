package de.tr7zw.javaorbit.server.connection.gamepackets.in;

import de.tr7zw.javaorbit.server.connection.packet.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReadyRequest extends Packet{

    private short readyType;
    private short MAP_LOADED = 0;
    private short HERO_LOADED = 1;
    
}
