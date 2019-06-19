package de.tr7zw.javaorbit.server;

import de.tr7zw.javaorbit.server.enums.Maps;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GateTarget {

	private Maps map;
	private int x;
	private int y;
	
	public Location getLocation(){
		return new Location(Server.getInstance().getMapManager().getInstance(map), x, y);
	}

}
