package de.tr7zw.javaorbit.server.connection.gamepackets.in;

import de.tr7zw.javaorbit.server.connection.packet.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SelectShipRequest extends Packet {

    private int id;
    
}
