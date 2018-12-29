package de.tr7zw.javaorbit.server.enums.collectables;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Ore { //Class1
ORE_RED(1),
ORE_BLUE(2),
ORE_YELLOW(3),
ORE_PALLADIUM(15)
	;
	private int id;
}
