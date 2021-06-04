package de.tr7zw.javaorbit.server.maps;

import java.util.HashMap;

import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutSetDrones;
import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutSetMap;
import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutShipRemove;
import de.tr7zw.javaorbit.server.enums.Maps;
import de.tr7zw.javaorbit.server.maps.entities.EntityLiving;
import de.tr7zw.javaorbit.server.player.Player;
import de.tr7zw.javaorbit.server.player.PlayerView;
import lombok.NonNull;

public class MapManager {

	private HashMap<Maps, MapInstance> maps = new HashMap<>();
	
	public MapInstance getInstance(Maps map) {
		maps.computeIfAbsent(map, k -> new MapInstance(k));
		return maps.get(map);
	}
	
	public void enterMapInstance(Player player, MapInstance map, int positionX, int positionY) {
		maps.values().forEach(inst -> inst.removeLiving(player));
		//player.getConnection().send("R", ""+player.getSession().getUserId());
		
		player.getPositionComponent().instance = map;
		player.getPositionComponent().x = positionX;
		player.getPositionComponent().y = positionY;
		for(EntityLiving ent : player.getPlayerView().getViewLiving())
			player.sendPacket(new PacketPlayOutShipRemove(ent.getId()));
		player.setPlayerView(new PlayerView());
		player.sendPacket(new PacketPlayOutSetMap(map.getMap()));
		player.updatePlayer();
		player.sendPacket(new PacketPlayOutSetDrones(player.getSession().getUserId(), player.getDroneFormationString()));
		player.getConnection().send("f|C|" + player.getSession().getUserId() + "|10|3||" + player.getName() + "|1500|6000|1|1|1|0|0|0| 0|n|d|1|3/2-25-25,3/4-25-25-25-25,3/2-25-25|");
		player.getConnection().send("0|m|" + map.getMap().getId() + "|" + player.getPositionComponent().x + "|" + player.getPositionComponent().y);
		map.addPlayer(player);
	}

	public void enterMap(Player player, Maps map, int positionX, int positionY) {
		maps.values().forEach(inst -> inst.removeLiving(player));
		MapInstance instance = getInstance(map);
		//player.getConnection().send("R", ""+player.getSession().getUserId());
		player.getPositionComponent().instance = instance;
        player.getPositionComponent().x = positionX;
        player.getPositionComponent().y = positionY;
		for(EntityLiving ent : player.getPlayerView().getViewLiving())
			player.sendPacket(new PacketPlayOutShipRemove(ent.getId()));
		player.setPlayerView(new PlayerView());
		player.sendPacket(new PacketPlayOutSetMap(map));
		player.updatePlayer();
		player.sendPacket(new PacketPlayOutSetDrones(player.getSession().getUserId(), player.getDroneFormationString()));
		player.getConnection().send("f|C|" + player.getSession().getUserId() + "|10|3||" + player.getName() + "|1500|6000|1|1|1|0|0|0| 0|n|d|1|3/2-25-25,3/4-25-25-25-25,3/2-25-25|");
		player.getConnection().send("0|m|" + map.getId() + "|" + player.getPositionComponent().x + "|" + player.getPositionComponent().y);
		instance.addPlayer(player);
	}
	
	public void removePlayer(@NonNull Player player) {
		for(MapInstance inst : maps.values()) {
			inst.removeLiving(player);
		}
	}

}
