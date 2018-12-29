package de.tr7zw.javaorbit.server.player;

import lombok.Getter;
import lombok.Setter;

public class PlayerData {

	@Getter @Setter private int credits = 25000000;
	@Getter @Setter private int uridium = 10000;
	@Getter @Setter private int jackpot = 0;
	@Getter private int honor = 2000000;
	@Getter private int exp = 310000000;
	@Getter private int level = 17;
	@Getter @Setter private boolean premium = true;
	
}
