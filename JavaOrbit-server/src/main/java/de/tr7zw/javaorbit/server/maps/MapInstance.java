package de.tr7zw.javaorbit.server.maps;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.signals.Signal;

import de.tr7zw.javaorbit.server.components.GateComponent;
import de.tr7zw.javaorbit.server.components.IdComponent;
import de.tr7zw.javaorbit.server.components.PositionComponent;
import de.tr7zw.javaorbit.server.components.StationComponent;
import de.tr7zw.javaorbit.server.connection.packet.PacketOut;
import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutShipRemove;
import de.tr7zw.javaorbit.server.enums.Gate;
import de.tr7zw.javaorbit.server.enums.Maps;
import de.tr7zw.javaorbit.server.enums.Station;
import de.tr7zw.javaorbit.server.enums.Version;
import de.tr7zw.javaorbit.server.maps.entities.EntityLegacy;
import de.tr7zw.javaorbit.server.maps.entities.EntityLiving;
import de.tr7zw.javaorbit.server.maps.listener.MapStaticListener;
import de.tr7zw.javaorbit.server.maps.systems.GateSystem;
import de.tr7zw.javaorbit.server.maps.systems.MovementSystem;
import de.tr7zw.javaorbit.server.maps.systems.RadiationSystem;
import de.tr7zw.javaorbit.server.maps.systems.StatusSystem;
import de.tr7zw.javaorbit.server.player.Player;
import lombok.Getter;
import lombok.extern.java.Log;

@Log
public class MapInstance {

    // Static data
	private static AtomicInteger ID_COUNTER = new AtomicInteger(0);
	private static final Random random = new Random();

	@Getter private Maps map;
	@Getter private int instanceId = ID_COUNTER.getAndIncrement(); 
	private InstanceThread thread = new InstanceThread(this);
	@Getter private Engine entityEngine = new Engine();
	@Getter private HashMap<Integer, Player> players = new HashMap<>();
	@Getter private HashMap<Integer, EntityLiving> livingEntities = new HashMap<>();
	//@Getter private HashMap<Integer, EntityCollectable> collectables = new HashMap<>();
	//@Getter private HashMap<Integer, EntityGate> gates = new HashMap<>();
	//@Getter private HashMap<EntityTarget, Integer> entityTargetAmount = new HashMap<>();
	@Getter private Set<Player> playersToAdd = Collections.synchronizedSet(new HashSet<>());
	// Events
	@Getter private Signal<Player> playerJoinEvent = new Signal<>();
	@Getter private Signal<Player> playerLeaveEvent = new Signal<>();

	// Systems
	@Getter private MovementSystem movementSystem = new MovementSystem();

	
	
	protected MapInstance(Maps map) {
		this.map = map;
		log.log(Level.INFO, "Created Instance id " + instanceId + " for map " + map.name());
		//addCollectable(new EntityCollectable(Collectable.EASTEREGG, new Location(this, 1963, 1967)));
		if(map == Maps.MAP1_1) {
			Entity station = entityEngine.createEntity();
			station.add(new PositionComponent(this, 1000, 1000));
			station.add(new StationComponent(Station.MMO_STATION));
		    entityEngine.addEntity(station);
			addGate(Gate.NORMAL, 18500,11500, Maps.MAP1_2, 1000, 1000);
			//entityTargetAmount.put(new EntityTarget(EntityStreuner.class, EntityStreuner::new), 60);
		}
		if(map == Maps.MAP2_1) {
		    Entity station = entityEngine.createEntity();
            station.add(new PositionComponent(this, 20000, 1000));
            station.add(new StationComponent(Station.EIC_STATION));
            entityEngine.addEntity(station);
		}
		if(map == Maps.MAP3_1) {
		    Entity station = entityEngine.createEntity();
            station.add(new PositionComponent(this, 20000, 12000));
            station.add(new StationComponent(Station.VRU_STATION));
            entityEngine.addEntity(station);
		}
		if(map == Maps.MAP1_2){
			addGate(Gate.NORMAL, 1000, 1000, Maps.MAP1_1, 18500,11500);
			//entityTargetAmount.put(new EntityTarget(EntityStreuner.class, EntityStreuner::new), 30);
			//entityTargetAmount.put(new EntityTarget(EntityLordakia.class, EntityLordakia::new), 30);	
		}
		initHandlers();
		thread.start();
	}

    private void initHandlers() {
	    playerJoinEvent.add(new MapStaticListener(this));
	    entityEngine.addSystem(movementSystem);
	    entityEngine.addSystem(new GateSystem());
	    entityEngine.addSystem(new StatusSystem());
	    entityEngine.addSystem(new RadiationSystem());
	}
	
	/*public Location getRandomLocation(){
		return new Location(this, random.nextInt(getMapWidth()), random.nextInt(getMapHeight()));
	}*/

	/*public void addNPC(EntityNPC npc) {
		this.livingEntities.put(npc.getId(), npc);
	}*/
	
	public void addPlayer(Player player) {
	    playersToAdd.add(player);
		this.players.put(player.getId(), player);
		this.livingEntities.put(player.getId(), player);
		log.log(Level.INFO, "Player '" + player.getName() + "' joined the map " + map.name());
		playerJoinEvent.dispatch(player);
	}
	
	public Player getPlayer(int id) {
		return players.get(id);
	}
	
	public EntityLiving getEntity(int id) {
		return livingEntities.get(id);
	}
	
	public void removeLiving(EntityLiving entity) {
	    playersToAdd.remove(entity);
		if(players.containsKey(entity.getId())) {
			for(Player p : players.values()) {
				p.sendPacket(new PacketPlayOutShipRemove(entity.getId()));
				p.getPlayerView().getViewLiving().remove(entity);
			}
			livingEntities.remove(entity.getId());
			if(players.remove(entity.getId()) != null) {
			    playerLeaveEvent.dispatch((Player) entity);
			    Entity playerEntity = ((Player)entity).getEntity();
			    entityEngine.removeEntity(playerEntity);
			}
			log.log(Level.INFO, "EntityLiving '" + entity.getName() + "' left the map " + map.name());
		}
	}
	
	/*public void addCollectable(EntityCollectable collectable) {
		collectables.put(collectable.getId(), collectable);
	}*/
	
	private void addGate(Gate gate, int x, int y, Maps targetMap, int targetX, int targetY) {
		Entity gateEntity = new Entity();
		gateEntity.add(new PositionComponent(this, x, y));
		gateEntity.add(new GateComponent(gate, targetMap, targetX, targetY));
		gateEntity.add(new IdComponent());
		entityEngine.addEntity(gateEntity);
	}

	/*public boolean inStation(Location loc, Faction faction){
		if(faction == Faction.MMO && map != Maps.MAP1_1)return false;
		if(faction == Faction.EIC && map != Maps.MAP2_1)return false;
		if(faction == Faction.VRU && map != Maps.MAP3_1)return false;
		for(Position stat : stations.keySet()){
			if(loc.inDistance(stat, 800))
				return true;
		}
		return false;
	}*/
	
	public int getMapWidth(){
		if(map == Maps.MAP4_4 || map == Maps.MAP4_5)return 42000;
		return 21000;
	}

	public int getMapHeight(){
		if(map == Maps.MAP4_4 || map == Maps.MAP4_5)return 28000;
		return 14000;
	}
	
	/*public void sendStatic(Player player) {
		for(EntityCollectable entity : collectables.values()) { //Nonstatic
			player.sendPacket(new PacketPlayOutSpawnCollectable(entity.getId(), entity.getType(), entity.getLocation().getX(), entity.getLocation().getY()));
		}
	}*/

	public void sendContextPacketVersion(Player player, Version targetVersion, PacketOut packet) {
		HashSet<Player> sendList = new HashSet<>();
		sendList.add(player);
		player.getPlayerView().getViewLiving().stream().filter(liv -> liv instanceof Player).filter(p -> ((Player)p).getConnection().getVersion().equals(targetVersion)).forEach(liv -> sendList.add((Player) liv));
		if(player.getPlayerView().getSelected() instanceof Player && ((Player)player.getPlayerView().getSelected()).getConnection().getVersion().equals(targetVersion))
			sendList.add((Player) player.getPlayerView().getSelected());
		for(Player p : sendList) {
			p.sendPacket(packet);
		}
	}

	public void sendContextPacket(EntityLegacy entity, PacketOut packet) {
		HashSet<Player> sendList = new HashSet<>();
		if(entity instanceof Player){
			Player player = (Player)entity;
			sendList.add(player);
			player.getPlayerView().getViewLiving().stream().filter(liv -> liv instanceof Player).forEach(liv -> sendList.add((Player) liv));
			if(player.getPlayerView().getSelected() instanceof Player)
				sendList.add((Player) player.getPlayerView().getSelected());
		}else{
			for(Player player : getPlayers().values()){
				if(player.getPlayerView().getViewLiving().contains(entity))
					sendList.add(player);
			}
		}
		
		for(Player p : sendList) {
			p.sendPacket(packet);
		}
	}
	
	@Override
	public String toString(){
		return map + ":" + instanceId;
	}

}
