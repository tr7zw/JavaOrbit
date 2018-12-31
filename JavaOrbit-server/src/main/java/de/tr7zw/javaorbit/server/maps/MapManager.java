package de.tr7zw.javaorbit.server.maps;

import java.util.HashMap;

import de.tr7zw.javaorbit.server.Location;
import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutSetDrones;
import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutSetMap;
import de.tr7zw.javaorbit.server.enums.Maps;
import de.tr7zw.javaorbit.server.player.Player;
import de.tr7zw.javaorbit.server.player.PlayerView;
import lombok.NonNull;

public class MapManager {

	private HashMap<Maps, MapInstance> maps = new HashMap<>();
	
	public MapInstance getInstance(Maps map) {
		maps.computeIfAbsent(map, k -> new MapInstance(k));
		return maps.get(map);
	}
	
	public void enterMap(Player player, Maps map, int positionX, int positionY) {
		maps.values().forEach(inst -> inst.removeLiving(player));
		MapInstance instance = getInstance(map);
		//player.getConnection().send("R", ""+player.getSession().getUserId());
		if(player.getLocation() == null) {
			player.setLocation(new Location(instance, positionX, positionY));
		}else {
			player.getLocation().setInstance(instance);
			player.getLocation().setX(positionX);
			player.getLocation().setY(positionY);
		}
		player.setPlayerView(new PlayerView());
		player.sendPacket(new PacketPlayOutSetMap(map));
		player.updatePlayer();
		player.sendPacket(new PacketPlayOutSetDrones(player.getSession().getUserId()));
		player.getConnection().send("f|C|" + player.getSession().getUserId() + "|10|3||" + player.getName() + "|1500|6000|1|1|1|0|0|0| 0|n|d|1|3/2-25-25,3/4-25-25-25-25,3/2-25-25|");
		player.getConnection().send("0|m|" + map.getId() + "|" + (int)player.getLocation().getX() + "|" + (int)player.getLocation().getY());
		instance.addPlayer(player);
	}
	
	public void removePlayer(@NonNull Player player) {
		for(MapInstance inst : maps.values()) {
			inst.removeLiving(player);
		}
	}

}
