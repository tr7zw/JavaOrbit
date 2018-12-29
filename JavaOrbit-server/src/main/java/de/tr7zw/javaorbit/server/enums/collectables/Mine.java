package de.tr7zw.javaorbit.server.enums.collectables;

import lombok.Getter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Getter
public enum Mine {
STANDARD(1),
EMP(2),
ANTI_SHIELD(3),
DIRECT_DMG(4),
PIRATE(5),
	;
	private int id;
}
