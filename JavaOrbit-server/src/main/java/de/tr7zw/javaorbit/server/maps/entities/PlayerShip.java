package de.tr7zw.javaorbit.server.maps.entities;

import de.tr7zw.javaorbit.server.player.Player;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class PlayerShip extends Ship{

	@NonNull @Getter private Player player;
	@Getter @Setter private int maxCargo = 4000;
	@Getter @Setter private int cargo = 0;

	@Override
	public int getDps() {
		switch(player.getAmmo()){
			case LCB10:
				return getBaseDmg();
			case MCB25:
				return getBaseDmg() * 2;
			case MCB50:
				return getBaseDmg() * 3;
			case USB100:
				return getBaseDmg() * 4;
			default:
				return getBaseDmg();
		}
	}

	public int getBaseDmg(){
		return 1000;
	}
	
}
