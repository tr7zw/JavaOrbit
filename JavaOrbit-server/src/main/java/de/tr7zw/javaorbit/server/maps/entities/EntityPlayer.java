package de.tr7zw.javaorbit.server.maps.entities;

import de.tr7zw.javaorbit.server.player.PlayerData;

public interface EntityPlayer extends EntityLiving{

	public PlayerData getPlayerData();
	public PlayerShip getPlayerShip();
	
	default public Ship getShip() {
		return getPlayerShip();
	}
	
}
