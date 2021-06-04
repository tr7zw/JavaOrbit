package de.tr7zw.javaorbit.server.connection.packet;

import java.io.DataInputStream;
import java.util.HashMap;
import java.util.Map;

import de.tr7zw.javaorbit.server.connection.PacketHandler;
import de.tr7zw.javaorbit.server.connection.PlayerConnection;
import de.tr7zw.javaorbit.server.connection.gamepackets.in.LoginRequest;
import de.tr7zw.javaorbit.server.connection.handler.LoginHandler;
import de.tr7zw.javaorbit.server.connection.parser.modern.ModernParser;
import de.tr7zw.javaorbit.server.connection.parser.retro.LegacyParser;
import lombok.extern.java.Log;

@Log
public class PacketManager {

    private static Map<String, LegacyParser<? extends Packet>> legacyParsers = new HashMap<>();
    private static Map<Short, ModernParser<? extends Packet>> modernParsers = new HashMap<>();
    private static Map<Class<? extends Packet>, PacketHandler<? extends Packet>> handlers = new HashMap<>(); 
    
    public static void registerLegacyParser(LegacyParser<? extends Packet> parser) {
        legacyParsers.put(parser.packetId(), parser);
    }
    
    public static void registerModernParser(ModernParser<? extends Packet> parser) {
        modernParsers.put(parser.packetId(), parser);
    }
    
    public static <T extends Packet> void registerHandler(Class<T> target, PacketHandler<T> handler) {
        handlers.put(target, handler);
    }
    
    public static void handleModernPacket(PlayerConnection connection, DataInputStream dataInputStream) {
        try {
            if(dataInputStream == null) return;
            short commandID = dataInputStream.readShort();
            ModernParser<? extends Packet> parser = modernParsers.get(commandID);
            if (parser != null) {
                Packet packet = parser.parsePacket(dataInputStream);
                if(packet != null) { // null = needs no processing
                    PacketHandler<Packet> handler = (PacketHandler<Packet>) handlers.get(packet.getClass());
                    if(handler != null) {
                        handler.process(connection, packet);
                    }else {
                       System.out.println("No handler for packet " + packet); 
                    }
                    
                }
            } else {
                log.info("Couldn't get parser for ID " + commandID);
                log.info(new String(dataInputStream.readAllBytes()));
            }
        } catch (Exception e) {
            log.info("Something went wrong getting the parser");
            e.printStackTrace();
        }
    }
    
    static {
        registerHandler(LoginRequest.class, new LoginHandler());
    }
    
}
