package de.tr7zw.javaorbit.server.maps.entities;

import de.tr7zw.javaorbit.server.enums.Ammo;
import de.tr7zw.javaorbit.server.enums.Faction;
import de.tr7zw.javaorbit.server.enums.LaserLook;
import de.tr7zw.javaorbit.server.enums.Rank;
import de.tr7zw.javaorbit.server.enums.Rings;
import de.tr7zw.javaorbit.server.enums.Title;
import de.tr7zw.javaorbit.server.player.Clan;

public interface EntityLiving extends EntityLegacy{

	public String getName();
	public Ship getShip();
	public LaserLook getLaserLook();
	public Ammo getAmmo();
	public Faction getFaction();
	public Title getTitle();
	public Rank getRank();
	public Rings getRings();
	public Clan getClan();
	public String getDroneFormationString();
	public void onAttack(EntityLiving attacker);
	public void damage(EntityLiving attacker, int amount);
	public void onDeath(EntityLiving attacker);
	
}
