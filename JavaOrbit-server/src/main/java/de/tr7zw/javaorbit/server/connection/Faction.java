package de.tr7zw.javaorbit.server.connection;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Faction {
NONE(0),
MMO(1),
EIC(2),
VRU(3),
SATURN(4) //?!?
	;
	private int id;
}
