package de.tr7zw.javaorbit.server.connection.packet;

import java.io.DataInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import de.tr7zw.javaorbit.server.connection.PacketHandler;
import de.tr7zw.javaorbit.server.connection.PlayerConnection;
import de.tr7zw.javaorbit.server.connection.gamepackets.in.JumpRequest;
import de.tr7zw.javaorbit.server.connection.gamepackets.in.LaserAttackRequest;
import de.tr7zw.javaorbit.server.connection.gamepackets.in.LaserStopRequest;
import de.tr7zw.javaorbit.server.connection.gamepackets.in.LoginRequest;
import de.tr7zw.javaorbit.server.connection.gamepackets.in.MoveRequest;
import de.tr7zw.javaorbit.server.connection.gamepackets.in.SelectAmmoRequest;
import de.tr7zw.javaorbit.server.connection.gamepackets.in.SelectShipRequest;
import de.tr7zw.javaorbit.server.connection.handler.JumpHandler;
import de.tr7zw.javaorbit.server.connection.handler.LaserAttackHandler;
import de.tr7zw.javaorbit.server.connection.handler.LaserStopHandler;
import de.tr7zw.javaorbit.server.connection.handler.LoginHandler;
import de.tr7zw.javaorbit.server.connection.handler.MoveHandler;
import de.tr7zw.javaorbit.server.connection.handler.SelectAmmoHandler;
import de.tr7zw.javaorbit.server.connection.handler.SelectShipHandler;
import de.tr7zw.javaorbit.server.connection.parser.LegacyParser;
import de.tr7zw.javaorbit.server.connection.parser.modern.ModernParser;
import de.tr7zw.javaorbit.server.connection.parser.retro.AttackRocketParser;
import de.tr7zw.javaorbit.server.connection.parser.retro.BuyParser;
import de.tr7zw.javaorbit.server.connection.parser.retro.ChangeConfigParser;
import de.tr7zw.javaorbit.server.connection.parser.retro.ClientSettingParser;
import de.tr7zw.javaorbit.server.connection.parser.retro.JumpParser;
import de.tr7zw.javaorbit.server.connection.parser.retro.LaserAttackParser;
import de.tr7zw.javaorbit.server.connection.parser.retro.LaserStopParser;
import de.tr7zw.javaorbit.server.connection.parser.retro.LoginParser;
import de.tr7zw.javaorbit.server.connection.parser.retro.LogoutCancelParser;
import de.tr7zw.javaorbit.server.connection.parser.retro.LogoutParser;
import de.tr7zw.javaorbit.server.connection.parser.retro.MoveParser;
import de.tr7zw.javaorbit.server.connection.parser.retro.PingParser;
import de.tr7zw.javaorbit.server.connection.parser.retro.ReadyParser;
import de.tr7zw.javaorbit.server.connection.parser.retro.SelectAmmoParser;
import de.tr7zw.javaorbit.server.connection.parser.retro.SelectParser;
import de.tr7zw.javaorbit.server.connection.parser.retro.SelectRocketParser;
import de.tr7zw.javaorbit.server.connection.parser.retro.SelectShipParser;
import de.tr7zw.javaorbit.server.connection.parser.retro.SelectShipRetroParser;
import de.tr7zw.javaorbit.server.connection.parser.retro.UseTechParser;
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
                tryProccessPacket(connection, packet);
            } else {
                log.info("Couldn't get parser for ID " + commandID);
                log.info(new String(dataInputStream.readAllBytes()));
            }
        } catch (Exception e) {
            log.info("Something went wrong getting the parser");
            e.printStackTrace();
        }
    }
    
    public static void handleLegacyPacket(PlayerConnection connection, String message) {
        if(message == null)return;
        Optional<Entry<String, LegacyParser<? extends Packet>>> parser = legacyParsers.entrySet().stream().filter(entry -> message.startsWith(entry.getKey())).findAny();
        if(parser.isPresent()) {
            Packet packet = parser.get().getValue().parsePacket(message);
            tryProccessPacket(connection, packet);
        }else {
            log.info("Couldn't get parser for packet: " + message);
        }
    }
    
    private static void tryProccessPacket(PlayerConnection connection, Packet packet) {
        if(packet != null) { // null = needs no processing
            PacketHandler<Packet> handler = (PacketHandler<Packet>) handlers.get(packet.getClass());
            if(handler != null) {
                handler.process(connection, packet);
            }else {
               System.out.println("No handler for packet " + packet); 
            }
            
        }
    }
    
    static {
        registerHandler(LoginRequest.class, new LoginHandler());
        registerHandler(JumpRequest.class, new JumpHandler());
        registerHandler(LaserAttackRequest.class, new LaserAttackHandler());
        registerHandler(LaserStopRequest.class, new LaserStopHandler());
        registerHandler(MoveRequest.class, new MoveHandler());
        registerHandler(SelectAmmoRequest.class, new SelectAmmoHandler());
        registerHandler(SelectShipRequest.class, new SelectShipHandler());
        
        registerLegacyParser(new AttackRocketParser());
        registerLegacyParser(new BuyParser());
        registerLegacyParser(new ChangeConfigParser());
        registerLegacyParser(new ClientSettingParser());
        registerLegacyParser(new JumpParser());
        registerLegacyParser(new LaserAttackParser());
        registerLegacyParser(new LaserStopParser());
        registerLegacyParser(new LoginParser());
        registerLegacyParser(new LogoutParser());
        registerLegacyParser(new LogoutCancelParser());
        registerLegacyParser(new MoveParser());
        registerLegacyParser(new PingParser());
        registerLegacyParser(new ReadyParser());
        registerLegacyParser(new SelectAmmoParser());
        registerLegacyParser(new SelectParser());
        registerLegacyParser(new SelectRocketParser());
        registerLegacyParser(new SelectShipParser());
        registerLegacyParser(new SelectShipRetroParser());
        registerLegacyParser(new UseTechParser());
    }
    
}
