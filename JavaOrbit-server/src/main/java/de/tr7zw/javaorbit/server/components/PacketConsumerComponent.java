package de.tr7zw.javaorbit.server.components;

import java.util.function.Consumer;

import com.badlogic.ashley.core.Component;

import de.tr7zw.javaorbit.server.connection.packet.PacketOut;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PacketConsumerComponent implements Component{

    public final Consumer<PacketOut> packetHandler;
    
}
