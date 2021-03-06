package de.tr7zw.javaorbit.server.maps;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import de.tr7zw.javaorbit.server.Server;
import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutLaserStop;
import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutShootLaser;
import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutStatus;
import de.tr7zw.javaorbit.server.enums.Version;
import de.tr7zw.javaorbit.server.maps.entities.EntityLiving;
import de.tr7zw.javaorbit.server.player.Player;
import de.tr7zw.javaorbit.server.player.PlayerView;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@RequiredArgsConstructor
@Log
public class InstanceThread extends Thread {

	@NonNull
	private MapInstance instance;
	private int tickrate = 20;
	private int tickwait = 1000 / tickrate;
	private int serverTick = 0;

	@Override
	public void run() {
		while (!Server.getInstance().isShutdown()) {
			serverTick++;
			try{
			/*for (EntityLiving living : instance.getLivingEntities().values()) {
				if (serverTick % 5 == 0 && living instanceof EntityNPC) {
					((EntityAI) living).updateAi();
					updateCombatAI((EntityNPC) living);
				}
			}*/
			for (Player player : instance.getPlayers().values()) {// Playertick
				if(!player.isDead()){
					updateViewdistancePlayer(player);
					updateCombat(player);
				}
			}
			/*if (serverTick % tickrate == 0) {// Entity Spawning
				for (Entry<EntityTarget, Integer> ent : instance.getEntityTargetAmount().entrySet()) {
					long amount = instance.getLivingEntities().values().stream()
							.filter(e -> ent.getKey().getEntityClass() == e.getClass()).count();
					amount = ent.getValue() - amount;
					while(amount > 0){
						instance.addNPC(ent.getKey().getCreator().apply(instance.getRandomLocation()));
						amount--;
					}
				}
			}*/
			Set<Player> toRemove = new HashSet<>();
			instance.getPlayersToAdd().forEach(p -> {
			    toRemove.add(p);
			    instance.getEntityEngine().addEntity(p.getEntity());
			});
			instance.getPlayersToAdd().removeAll(toRemove);// FIXME
			instance.getEntityEngine().update(tickwait);
			try {
				Thread.sleep(tickwait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}catch(Exception ex){
			log.log(Level.WARNING, "Caught Exception during instance tick!", ex);
		}
		}
	}

	/*private void updateCombatAI(EntityNPC entity) {
		if(entity.getTarget() != null && entity.isAgressive() && serverTick % tickrate == 0 && !entity.isOutOfReach() && !entity.isTargetInSavezone()) {
			entity.setWasAttacking(true);
			instance.sendContextPacket(entity, new PacketPlayOutShootLaser(entity.getId(), entity.getTarget().getId(), entity.getAmmo(), null, null));
			entity.getTarget().onAttack(entity);
		}

		if((entity.isOutOfReach() || entity.isTargetInSavezone()) && entity.isWasAttacking()) {
			entity.getLocation().getInstance().sendContextPacket(entity, new PacketPlayOutLaserStop(entity.getId(), entity.getTarget().getId())); //TODO: FIXME: Doesn't seem to work for other players, keeps shooting
			entity.setWasAttacking(false);
		}
	}*/

	private void updateCombat(Player player) {
		if(player.getPlayerView().isAttacking() && player.getPlayerView().getSelected() != null && serverTick % tickrate == 0) {
			player.getPlayerView().setWasAttacking(true);
			EntityLiving target = player.getPlayerView().getSelected();
			instance.sendContextPacketVersion(player, Version.REVOLUTION2D, new PacketPlayOutShootLaser(player.getId(), target.getId(), player.getAmmo(), true, true));
			instance.sendContextPacketVersion(player, Version.RETRO, new PacketPlayOutShootLaser(player.getId(), target.getId(), player.getAmmo(), null, null));
			player.getPlayerView().getSelected().onAttack(player);
		}

		if(!player.getPlayerView().isAttacking() && player.getPlayerView().isWasAttacking()) {
			player.getPositionComponent().instance.sendContextPacket(player, new PacketPlayOutLaserStop(player.getId(), player.getPlayerView().getSelectedId())); //TODO: FIXME: Doesn't seem to work for other players, keeps shooting
			player.getPlayerView().setWasAttacking(false);
		}
	}

	private void updateViewdistancePlayer(Player player) {
		PlayerView view = player.getPlayerView();
		for(EntityLiving other : instance.getLivingEntities().values()) {
			if(other.getId() != player.getId()) {
				if(view.getViewLiving().contains(other)) {//Can see Entity
					boolean asTarget = view.getSelectedId() == other.getId() || (other instanceof Player && ((Player)other).getPlayerView().getSelectedId() == player.getId());
					/*if(asTarget || player.getPositionComponent().inDistance(other.get, player.getViewDistance())) {//move
						if(other.getTargetLocation() != null && view.getLastLoc().getOrDefault(other, 0) != other.getTargetLocation().hashCode()) {//TODO: hack
							view.getLastLoc().put(other, other.getTargetLocation().hashCode());
							int distance = other.getLocation().distance(other.getTargetLocation());
							int time = distance / other.getShip().getSpeed() * 1080;
							if(time == 0)time = 1000;
							player.sendPacket(new PacketPlayOutShipMove(other.getId(), other.getTargetLocation().getX(), other.getTargetLocation().getY(), time));
						} // TODO ?!?
					} else {//despawn
						if(!asTarget) {
							view.getViewLiving().remove(other);
							player.sendPacket(new PacketPlayOutShipRemove(other.getId()));
						}
					}*/
				} else { //Can't see player
					/*if(player.getPositionComponent().inDistance(other.getLocation(), player.getViewDistance())) {//Should see him
						view.getViewLiving().add(other);
						player.sendPacket(new PacketPlayOutSpawnShip(other.getId(), other.getShip().getType(), other.getLaserLook(), other.getName(), other.getLocation().getX(), other.getLocation().getY(), other.getFaction(), other.getFaction() == player.getFaction(), false, other.getRank(), other.getRings(), ClanStatus.NEUTRAL, other.getClan()));
						player.sendPacket(new PacketPlayOutShipMove(other.getId(), other.getLocation().getX(), other.getLocation().getY(), 1));
						player.sendPacket(new PacketPlayOutSetDrones(other.getId(), other.getDroneFormationString()));
						player.sendPacket(new PacketPlayOutPermanentTitle(other.getId(), other.getTitle()));
					}*/
				}
			}
		}
	}

}
