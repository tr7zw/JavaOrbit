package de.tr7zw.javaorbit.server;

import de.tr7zw.javaorbit.server.maps.MapInstance;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Location implements Cloneable{
	private MapInstance instance;
	private int x;
	private int y;

	public boolean inDistance(Location location, int distance) {
		return Math.abs(Math.pow(x - location.x, 2) + Math.pow(y - location.y, 2)) <= Math.pow(distance, 2);
	}

	public boolean inDistance(Position position, int distance) {
		return Math.abs(Math.pow(x - position.getX(), 2) + Math.pow(y - position.getY(), 2)) <= Math.pow(distance, 2);
	}

	public int distance(Location location) {
		return (int) Math.sqrt(Math.abs(Math.pow(x - location.x, 2) + Math.pow(y - location.y, 2)));
	}

	@Override
	public Location clone() {
		return new Location(instance, x, y);
	}

}
