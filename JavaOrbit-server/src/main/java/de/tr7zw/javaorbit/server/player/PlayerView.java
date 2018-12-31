package de.tr7zw.javaorbit.server.player;

import java.util.HashMap;
import java.util.HashSet;

import de.tr7zw.javaorbit.server.maps.entities.EntityLiving;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerView {

	private HashSet<EntityLiving> viewLiving = new HashSet<>();
	private HashMap<EntityLiving, Integer> lastLoc = new HashMap<>();
	private int selectedId = 0;
	private EntityLiving selected = null;
	private boolean attacking = false;
	private boolean wasAttacking = false;
	
}
