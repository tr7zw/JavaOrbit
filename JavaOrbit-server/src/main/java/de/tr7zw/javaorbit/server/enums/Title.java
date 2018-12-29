package de.tr7zw.javaorbit.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Title {
ADMIN_TITLE("admin_title"),
CM_TITLE("cm_title"),
JOKER("test1234567"),
REAPER("test123456"),
LIVING_LEGEND("test12345"),
KING_OF_PVP("test1234"),
SPACE_HUNTER("test123"),
NONE(""),

;
	private String id;
}
