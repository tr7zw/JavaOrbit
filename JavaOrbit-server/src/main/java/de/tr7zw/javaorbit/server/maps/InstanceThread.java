package de.tr7zw.javaorbit.server.maps;

import de.tr7zw.javaorbit.server.Server;
import de.tr7zw.javaorbit.server.connection.packet.out.PacketOutLaserStop;
import de.tr7zw.javaorbit.server.connection.packet.out.PacketOutPermanentTitle;
import de.tr7zw.javaorbit.server.connection.packet.out.PacketOutSetDrones;
import de.tr7zw.javaorbit.server.connection.packet.out.PacketOutShipData;
import de.tr7zw.javaorbit.server.connection.packet.out.PacketOutShipMove;
import de.tr7zw.javaorbit.server.connection.packet.out.PacketOutShipRemove;
import de.tr7zw.javaorbit.server.connection.packet.out.PacketOutShootLaser;
import de.tr7zw.javaorbit.server.connection.packet.out.PacketOutSpawnShip;
import de.tr7zw.javaorbit.server.enums.Ammo;
import de.tr7zw.javaorbit.server.enums.ClanStatus;
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
	private int serverTick = 0;

	@Override
	public void run() {
		while(!Server.getInstance().isShutdown()) {
			serverTick++;
			for(EntityLiving living : instance.getLivingEntities().values()) {
				updatePosition(living);//Update position
			}
			for(Player player : instance.getPlayers().values()) {//Playertick
				updateViewdistancePlayer(player);
				updateCombat(player);
			}
			try {
				Thread.sleep(tickwait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void updateCombat(Player player) {
		if(player.getPlayerView().isAttacking() && player.getPlayerView().getSelected() != null && serverTick % tickrate == 0) {
			player.getPlayerView().setWasAttacking(true);
			EntityLiving target = player.getPlayerView().getSelected();
			instance.sendContextPacket(player, new PacketOutShootLaser(player.getId(), target.getId(), Ammo.USB100, true, true));
			player.sendPacket(new PacketOutShipData(target.getId(), target.getName(), target.getShip().getShield(), target.getShip().getMaxShield(), target.getShip().getHp(), target.getShip().getMaxHp()));
		}

		if(!player.getPlayerView().isAttacking() && player.getPlayerView().isWasAttacking()) {
			player.getLocation().getInstance().sendContextPacket(player, new PacketOutLaserStop(player.getId(), player.getPlayerView().getSelectedId())); //TODO: FIXME: Doesn't seem to work for other players, keeps shooting
			player.getPlayerView().setWasAttacking(false);
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
					boolean asTarget = view.getSelectedId() == other.getId() || (other instanceof Player && ((Player)other).getPlayerView().getSelectedId() == player.getId());
					if(asTarget || player.getLocation().inDistance(other.getLocation(), player.getViewDistance())) {//move
						if(other.getTargetLocation() != null && view.getLastLoc().getOrDefault(other, 0) != other.getTargetLocation().hashCode()) {//TODO: hack
							view.getLastLoc().put(other, other.getTargetLocation().hashCode());
							int distance = other.getLocation().distance(other.getTargetLocation());
							int time = distance / other.getShip().getSpeed() * 1080;
							if(time == 0)time = 1000;
							player.sendPacket(new PacketOutShipMove(other.getId(), other.getTargetLocation().getX(), other.getTargetLocation().getY(), time));
						}
					} else {//despawn
						if(!asTarget) {
							view.getViewLiving().remove(other);
							player.sendPacket(new PacketOutShipRemove(other.getId()));
						}
					}
				} else { //Can't see player
					if(player.getLocation().inDistance(other.getLocation(), player.getViewDistance())) {//Should see him
						view.getViewLiving().add(other);
						player.sendPacket(new PacketOutSpawnShip(other.getId(), other.getShip().getType(), other.getName(), other.getLocation().getX(), other.getLocation().getY(), other.getFaction(), other.getFaction() == player.getFaction(), true, other.getRank(), other.getRings(), ClanStatus.NEUTRAL, other.getClan()));
						player.sendPacket(new PacketOutSetDrones(other.getId()));
						player.sendPacket(new PacketOutPermanentTitle(other.getId(), other.getTitle()));
					}
				}
			}
		}
	}

}
