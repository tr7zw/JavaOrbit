package de.tr7zw.javaorbit.server.maps;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;

import de.tr7zw.javaorbit.server.GateTarget;
import de.tr7zw.javaorbit.server.Location;
import de.tr7zw.javaorbit.server.Position;
import de.tr7zw.javaorbit.server.connection.packet.PacketOut;
import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutShipRemove;
import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutSpawnCollectable;
import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutSpawnGate;
import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutSpawnStation;
import de.tr7zw.javaorbit.server.enums.Gate;
import de.tr7zw.javaorbit.server.enums.Maps;
import de.tr7zw.javaorbit.server.enums.Station;
import de.tr7zw.javaorbit.server.enums.collectables.Collectable;
import de.tr7zw.javaorbit.server.maps.entities.EntityCollectable;
import de.tr7zw.javaorbit.server.maps.entities.EntityGate;
import de.tr7zw.javaorbit.server.maps.entities.EntityLiving;
import de.tr7zw.javaorbit.server.npc.EntityNPC;
import de.tr7zw.javaorbit.server.player.Player;
import lombok.Getter;
import lombok.extern.java.Log;

@Log
public class MapInstance {

	private static AtomicInteger ID_COUNTER = new AtomicInteger(0);
	@Getter private Maps map;
	@Getter private int instanceId = ID_COUNTER.getAndIncrement(); 
	private InstanceThread thread = new InstanceThread(this);
	@Getter private HashMap<Integer, Player> players = new HashMap<>();
	@Getter private HashMap<Integer, EntityLiving> livingEntities = new HashMap<>();
	@Getter private HashMap<Integer, EntityCollectable> collectables = new HashMap<>();
	private HashMap<Position, Station> stations = new HashMap<>();
	@Getter private HashMap<Integer, EntityGate> gates = new HashMap<>();
	
	protected MapInstance(Maps map) {
		this.map = map;
		log.log(Level.INFO, "Created Instance id " + instanceId + " for map " + map.name());
		addCollectable(new EntityCollectable(Collectable.EASTEREGG, new Location(this, 1963, 1967)));
		if(map == Maps.MAP1_1) {
			stations.put(new Position(1000, 1000), Station.MMO_STATION);
			addGate(new EntityGate(Gate.NORMAL, new Location(this, 18500,11500), new GateTarget(Maps.MAP1_2, 1000, 1000)));
		}
		if(map == Maps.MAP2_1) {
			stations.put(new Position(20000, 1000), Station.EIC_STATION);
		}
		if(map == Maps.MAP3_1) {
			stations.put(new Position(20000, 12000), Station.VRU_STATION);
		}
		if(map == Maps.MAP1_2){
			addGate(new EntityGate(Gate.NORMAL, new Location(this, 1000, 1000), new GateTarget(Maps.MAP1_1, 18500,11500)));
		}
		thread.start();
	}
	
	public void addNPC(EntityNPC npc) {
		this.livingEntities.put(npc.getId(), npc);
	}
	
	public void addPlayer(Player player) {
		this.players.put(player.getSession().getUserId(), player);
		this.livingEntities.put(player.getSession().getUserId(), player);
		log.log(Level.INFO, "Player '" + player.getName() + "' joined the map " + map.name());
		sendStatic(player);
	}
	
	public Player getPlayer(int id) {
		return players.get(id);
	}
	
	public EntityLiving getEntity(int id) {
		return livingEntities.get(id);
	}
	
	public void removeLiving(EntityLiving entity) {
		if(players.containsKey(entity.getId())) {
			for(Player p : players.values()) {
				p.sendPacket(new PacketPlayOutShipRemove(entity.getId()));
				p.getPlayerView().getViewLiving().remove(entity);
			}
			players.remove(entity.getId());
			livingEntities.remove(entity.getId());
			log.log(Level.INFO, "EntityLiving '" + entity.getName() + "' left the map " + map.name());
		}
	}
	
	public void addCollectable(EntityCollectable collectable) {
		collectables.put(collectable.getId(), collectable);
	}
	
	public void addGate(EntityGate gate) {
		gates.put(gate.getId(), gate);
	}

	public EntityGate getGateAt(Location location){
		if(!location.getInstance().equals(this))return null;
		for(EntityGate gate : gates.values()){
			if(gate.getLocation().inDistance(location, 300))
				return gate;
		}
		return null;
	}
	
	public void sendStatic(Player player) {
		for(EntityCollectable entity : collectables.values()) { //Nonstatic
			player.sendPacket(new PacketPlayOutSpawnCollectable(entity.getId(), entity.getType(), entity.getLocation().getX(), entity.getLocation().getY()));
		}
		for(Entry<Position, Station> entry : stations.entrySet()) {
			player.sendPacket(new PacketPlayOutSpawnStation(entry.getValue().name(), entry.getValue(), entry.getKey().getX(), entry.getKey().getY()));
		}
		for(EntityGate gate : gates.values()) {
			player.sendPacket(new PacketPlayOutSpawnGate(gate.getId(), gate.getGate(), gate.getLocation().getX(), gate.getLocation().getY()));
		}
	}
	
	public void sendContextPacket(Player player, PacketOut packet) {
		HashSet<Player> sendList = new HashSet<>();
		sendList.add(player);
		player.getPlayerView().getViewLiving().stream().filter(liv -> liv instanceof Player).forEach(liv -> sendList.add((Player) liv));
		if(player.getPlayerView().getSelected() instanceof Player)
			sendList.add((Player) player.getPlayerView().getSelected());
		for(Player p : sendList) {
			p.sendPacket(packet);
		}
	}
	
	@Override
	public String toString(){
		return map + ":" + instanceId;
	}

}
