package de.tr7zw.javaorbit.server.maps;

import de.tr7zw.javaorbit.server.Server;
import de.tr7zw.javaorbit.server.connection.packet.out.PacketOutPermanentTitle;
import de.tr7zw.javaorbit.server.connection.packet.out.PacketOutSetDrones;
import de.tr7zw.javaorbit.server.connection.packet.out.PacketOutShipMove;
import de.tr7zw.javaorbit.server.connection.packet.out.PacketOutShipRemove;
import de.tr7zw.javaorbit.server.connection.packet.out.PacketOutSpawnShip;
import de.tr7zw.javaorbit.server.maps.entities.EntityLiving;
import de.tr7zw.javaorbit.server.player.Player;
import de.tr7zw.javaorbit.server.player.PlayerView;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InstanceThread extends Thread{

	@NonNull private MapInstance instance;
	private int tickrate = 20;
	private int tickwait = 1000/tickrate;
	
	@Override
	public void run() {
		while(!Server.getInstance().isShutdown()) {
			for(EntityLiving living : instance.getLivingEntities().values()) {
				updatePosition(living);
			}
			for(Player player : instance.getPlayers().values()) {//Playertick
				//Update position
				updateViewdistancePlayer(player);
			}
			try {
				Thread.sleep(tickwait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void updatePosition(EntityLiving living) { 
		if(living.isMoving()) {
			if(System.currentTimeMillis() - living.getMovingStartTime() > living.getMoveTime()) {
				living.setLocation(living.getTargetLocation());
				living.setMoving(false);
			}else {
				int offsetX = living.getTargetLocation().getX() - living.getStartLocation().getX();
				int offsetY = living.getTargetLocation().getY() - living.getStartLocation().getY();
				long timeSinceStart = System.currentTimeMillis() - living.getMovingStartTime();
				double progress = (double)timeSinceStart / (double)living.getMoveTime();
				offsetX *= progress;
				offsetY *= progress;
				living.getLocation().setX(living.getStartLocation().getX() + offsetX);
				living.getLocation().setY(living.getStartLocation().getY() + offsetY);
			}
		}
	}
	
	private void updateViewdistancePlayer(Player player) {
		PlayerView view = player.getPlayerView();
		for(EntityLiving other : instance.getLivingEntities().values()) {
			if(other.getId() != player.getId()) {
				if(view.getViewLiving().contains(other)) {//Can see Entity
					if(player.getLocation().inDistance(other.getLocation(), player.getViewDistance())) {//move
						if(view.getLastLoc().getOrDefault(other, 0) != other.getTargetLocation().hashCode()) {//TODO: hack
							view.getLastLoc().put(other, other.getTargetLocation().hashCode());
							int distance = other.getLocation().distance(other.getTargetLocation());
							int time = distance / other.getShip().getSpeed() * 1080;
							if(time == 0)time = 1000;
							player.sendPacket(new PacketOutShipMove(other.getId(), other.getTargetLocation().getX(), other.getTargetLocation().getY(), time));
						}
					} else {//despawn
						view.getViewLiving().remove(other);
						player.sendPacket(new PacketOutShipRemove(other.getId()));
					}
				} else { //Can't see player
					if(player.getLocation().inDistance(other.getLocation(), player.getViewDistance())) {//Should see him
						view.getViewLiving().add(other);
						player.sendPacket(new PacketOutSpawnShip(other.getId(), other.getShip().getType(), "[" + other.getClan() + "] " + other.getName(), other.getLocation().getX(), other.getLocation().getY(), other.getFaction(), other.getFaction() == player.getFaction(), other.getRank(), other.getRings()));
						player.sendPacket(new PacketOutSetDrones(other.getId()));
						player.sendPacket(new PacketOutPermanentTitle(other.getId(), other.getTitle()));
					}
				}
			}
		}
	}
	
}
