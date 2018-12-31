package de.tr7zw.javaorbit.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ClanStatus {
NEUTRAL(0),
PEACE(1),
NAP(2),
WAR(3),
	;
	private int id;
}
