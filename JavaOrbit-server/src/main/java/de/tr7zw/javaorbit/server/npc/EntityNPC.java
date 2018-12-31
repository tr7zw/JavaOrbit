package de.tr7zw.javaorbit.server.npc;

import java.util.concurrent.atomic.AtomicInteger;

import de.tr7zw.javaorbit.server.Location;
import de.tr7zw.javaorbit.server.connection.Faction;
import de.tr7zw.javaorbit.server.enums.Rank;
import de.tr7zw.javaorbit.server.enums.Rings;
import de.tr7zw.javaorbit.server.enums.Title;
import de.tr7zw.javaorbit.server.maps.entities.EntityAI;
import de.tr7zw.javaorbit.server.maps.entities.Ship;
import de.tr7zw.javaorbit.server.player.Clan;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public abstract class EntityNPC implements EntityAI{

	private int id = Counter.COUNTER.decrementAndGet();
	@NonNull private String name;
	@NonNull private Ship ship;
	@NonNull private Location location;
	private Location targetLocation = location;
	private boolean moving = false;
	private long movingStartTime = 0;
	private int moveTime = 0;
	private Location startLocation = location;
	private Rank rank = Rank.NONE;
	private Rings rings = Rings.NONE;
	private Faction faction = Faction.NONE;
	private Title title = Title.NONE;
	private Clan clan;

	private static class Counter{
		private static AtomicInteger COUNTER = new AtomicInteger(-1);
	}
	
}

