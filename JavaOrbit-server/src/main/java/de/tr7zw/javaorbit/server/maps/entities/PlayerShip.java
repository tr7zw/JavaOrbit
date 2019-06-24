package de.tr7zw.javaorbit.server.maps.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class PlayerShip extends Ship{

	@Getter @Setter private int maxCargo = 4000;
	@Getter @Setter private int cargo = 0;

	@Override
	public int getDps() {
		return 1000;
	}
	
}
