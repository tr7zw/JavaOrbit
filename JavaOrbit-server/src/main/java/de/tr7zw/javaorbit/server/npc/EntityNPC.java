//package de.tr7zw.javaorbit.server.npc;
//
//import java.util.Random;
//import java.util.concurrent.atomic.AtomicInteger;
//
//import de.tr7zw.javaorbit.server.Location;
//import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutLaserStop;
//import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutShipData;
//import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutShipKilled;
//import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutShipRemove;
//import de.tr7zw.javaorbit.server.enums.Faction;
//import de.tr7zw.javaorbit.server.enums.LaserLook;
//import de.tr7zw.javaorbit.server.enums.Rank;
//import de.tr7zw.javaorbit.server.enums.Rings;
//import de.tr7zw.javaorbit.server.enums.Title;
//import de.tr7zw.javaorbit.server.maps.entities.EntityAI;
//import de.tr7zw.javaorbit.server.maps.entities.EntityLiving;
//import de.tr7zw.javaorbit.server.maps.entities.Ship;
//import de.tr7zw.javaorbit.server.player.Clan;
//import de.tr7zw.javaorbit.server.player.Player;
//import lombok.Getter;
//import lombok.NonNull;
//import lombok.RequiredArgsConstructor;
//import lombok.Setter;
//
//@RequiredArgsConstructor
//@Getter
//@Setter
//public abstract class EntityNPC implements EntityAI{
//
//	private final static Random random = new Random();
//
//	private int id = Counter.COUNTER.decrementAndGet();
//	@NonNull private String name;
//	@NonNull private Ship ship;
//	@NonNull private Location location;
//	private Location targetLocation = location;
//	private boolean moving = false;
//	private long movingStartTime = 0;
//	private int moveTime = 0;
//	private Location startLocation = location;
//	private Rank rank = Rank.NONE;
//	private Rings rings = Rings.NONE;
//	private Faction faction = Faction.NONE;
//	private Title title = Title.NONE;
//	private LaserLook laserLook = LaserLook.NORMAL;
//	private Clan clan;
//	@Setter private EntityLiving target = null;
//	private boolean outOfReach = true;
//	private boolean agressive = false;
//	private boolean wasAttacking = false;
//	private boolean targetInSavezone = false;
//	private boolean passive = false;
//
//	private static class Counter{
//		private static AtomicInteger COUNTER = new AtomicInteger(-1);
//	}
//	
//	public void moveTo(Location moveLoc){
//		startLocation = location.clone();
//		targetLocation = moveLoc;
//		setMoving(true);
//		int distance = getLocation().distance(getTargetLocation());
//		int time = distance / getShip().getSpeed() * 1080; //At least while testing 1080 was fitting the clients speed
//		if(time == 0)time = 1000;
//		setMoveTime(time);
//		setMovingStartTime(System.currentTimeMillis());
//	}
//
//	@Override
//	public String getDroneFormationString(){
//		return "";
//	}
//
//	@Override
//	public void updateAi() {
//		if(isDead())return;
//		if(target == null){
//			for(Player player : getLocation().getInstance().getPlayers().values()){
//				if(!player.inDemilitarizedZone() && player.getLocation().inDistance(location, 1500)){
//					target = player;
//					if(!passive)
//						agressive = true;
//					break;
//				}
//			}
//		}
//		if(target != null){
//			if(target.getLocation() == null || target.getLocation().getInstance() != location.getInstance() || (!wasAttacking && targetInSavezone) || !target.getLocation().inDistance(location, 1700)){
//				target = null;
//				targetInSavezone = false;
//				if(passive)
//					agressive = false;
//				return;
//			}
//			if(target instanceof Player)
//				targetInSavezone = ((Player)target).inDemilitarizedZone();
//
//			outOfReach = !target.getLocation().inDistance(location, 300);
//			if(!target.getLocation().inDistance(location, 250) && (targetLocation == null || !target.getLocation().inDistance(targetLocation, 250))){
//				final double angle = Math.toRadians(Math.random() * 360);
//            	final int newPosX = (int) (target.getLocation().getX() +(250 * Math.cos(angle)));
//				final int newPosY = (int) (target.getLocation().getY() + (250 * Math.sin(angle)));
//				moveTo(new Location(location.getInstance(), newPosX, newPosY));
//			}
//		} else if(!moving && Math.random() > 0.8){ //Random stroll
//			moveTo(location.getInstance().getRandomLocation());
//		}
//	}
//
//	@Override
//    public void onAttack(EntityLiving attacker) {
//		if(passive)
//			agressive = true;
//		if(target == null)
//			target = attacker;
//		int dmg = attacker.getShip().getDps();
//		dmg *= 1f-((float)random.nextInt(20)/100f);
//		damage(attacker, dmg);
//		
//    }
//
//    @Override
//    public void damage(EntityLiving attacker, int amount) {
//		int shieldDmg = (int) (getShip().getShieldStrength() * amount);
//		int directDmg = amount - shieldDmg;
//		int newShield = getShip().getShield() - shieldDmg;
//		if(newShield < 0){
//			directDmg += Math.abs(newShield);
//			newShield = 0;
//		}
//		getShip().setShield(newShield);
//		getShip().setHp(Math.max(getShip().getHp()-directDmg, 0));
//		if(getShip().getHp() <= 0){
//			onDeath(attacker);
//		}
//		if(attacker instanceof Player){
//			Player player = (Player) attacker;
//			player.getConnection().send("0|H|" + Math.max(1, ship.getShield()) + "|" + getShip().getHp() + "|" + this.getId() + "|" + amount);
//			player.sendPacket(new PacketPlayOutShipData(getId(), getName(),  getShip().getHp(), getShip().getMaxHp(), getShip().getShield(), getShip().getMaxShield()));
//			if(isDead()){
//				player.getPlayerView().setAttacking(false);
//				player.getPlayerView().setSelected(null);
//			}
//		}
//    }
//
//	public boolean isDead(){
//		return getShip().getHp() <= 0;
//	}
//
//	@Override
//    public void onDeath(EntityLiving attacker) {
//		outOfReach = true;
//		moving = false;
//		getLocation().getInstance().sendContextPacket(this, new PacketPlayOutShipKilled(this.getId()));
//		getLocation().getInstance().sendContextPacket(this, new PacketPlayOutLaserStop(attacker.getId(), this.getId()));
//		getLocation().getInstance().sendContextPacket(this, new PacketPlayOutLaserStop(this.getId(), attacker.getId()));
//		getLocation().getInstance().sendContextPacket(this, new PacketPlayOutShipRemove(this.getId()));
//		getLocation().getInstance().removeLiving(this);
//    }
//
//
//}
//
