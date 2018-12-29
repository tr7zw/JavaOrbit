package de.tr7zw.javaorbit.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gate {
NORMAL(1),
ALPHA(2),
BETA(3),
GAMMA(4),
DELTA(5),
BIRTHDAY(11),
BIRTHDAY2(13),
ORBSTER(12),
TDM1(21),
TDM2(22),
GROUP(34),
INVASION1(41),
INVASION2(42),
INVASION3(43),
PIRATE(51),
PIRATE_BROKEN(52)
	;
	private int id;
}
