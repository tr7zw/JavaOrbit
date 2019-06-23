package de.tr7zw.javaorbit.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Ammo {
LCB10(0),
MCB25(1),
MCB50(2),
USB100(3),
SAB50(4),
SANEJIEWS(5),
RSB75(6)

;
	private int id;

	public static Ammo fromId(int targetId){
		for(Ammo a : values())
			if(a.id == targetId)
				return a;
		return LCB10;
	}
}
