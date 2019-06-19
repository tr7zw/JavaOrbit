package de.tr7zw.javaorbit.server.connection.packet;

import java.util.logging.Level;

import de.tr7zw.javaorbit.server.connection.PacketType;
import lombok.extern.java.Log;

@Log
public class PacketParser {

	public static Packet parse(String data) {
		PacketType type = PacketType.getType(data);
		if(type == PacketType.UNKNOWN) {
			log.log(Level.WARNING, "Unknown packet: " + data);
			return null;
		}
		try {
			return (Packet) type.getHandlerClass().getConstructors()[0].newInstance(data);
		} catch (Exception e) {
			log.log(Level.SEVERE, "Error while parsing Packet of type '" + type + "' with the data '" + data + "'!", e);
		}
		return null;
	}
	
}
