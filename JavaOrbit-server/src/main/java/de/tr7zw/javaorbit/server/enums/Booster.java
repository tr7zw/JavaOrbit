package de.tr7zw.javaorbit.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Booster {
XP(1),
HONOR(2),
DAMAGE(3),
SHIELD(4),
REPAIR(5),
SHIELD_RECOVERY(6),
RESOURCE(7),
HITPOINTS(8),
QUEST_REWARD(9),
BONUS_BOX(10)
	;
	private int id;
}
