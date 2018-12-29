package de.tr7zw.javaorbit.server.enums.collectables;

import lombok.Getter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Getter
public enum Collectable { //Class0
CARGOBOX_STEALING(0),
CARGOBOX(1),
BONUSBOX(2),
EASTEREGG(3),
PUMPKIN_BIG(5),
PUMPKIN_STD(6),
TURKEY(7),
XMASSTAR_BIG(8),
XMASSTAR_STD(9),
FLOWER_MOTHERSDAY(10),
CRESTANDSTAR(14),
POLISH_FLAG(15),
WINTERGIFTBOX(16),
CARNIVALBOX(17),
BONUSBOXSUN(19),
PETWEEKBOX(20),//Broken
PIRATEBOOTYBOX(21),
	;
	private int id;
}
