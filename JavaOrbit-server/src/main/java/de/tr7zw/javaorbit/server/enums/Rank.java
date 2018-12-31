package de.tr7zw.javaorbit.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Rank {
	NONE(0),
	BASIC_SPACE_PILOT(1),
	SPACE_PILOT(2),
	CHIEF_SPACE_PILOT(3),
	BASIC_SERGEANT(4),
	SERGEANT(5),
	CHIEF_SERGEANT(6),
	BASIC_LIEUTENANT(7),
	LIEUTENANT(8),
	CHIEF_LIEUTENANT(9),
	BASIC_CAPTAIN(10),
	CAPTAIN(11),
	CHIEF_CAPTAIN(12),
	BASIC_MAJOR(13),
	MAJOR(14),
	CHIEF_MAJOR(15),
	BASIC_COLONEL(16),
	COLONEL(17),
	CHIEF_COLONEL(18),
	BASIC_GENERAL(19),
	GENERAL(20),
	ADMIN(21),
	OUTLAW(22),
	
	;
	private int id;
}
