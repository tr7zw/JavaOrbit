package de.tr7zw.javaorbit.server.enums;

import lombok.AllArgsConstructor;

import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Station {
MMO_STATION(1),
EIC_STATION(2),
VRU_STATION(3),
HEALING_STATION(4),
GREEN_BEACON(5),
PIRATE_STATION(6),

	;
	private int id;
}
