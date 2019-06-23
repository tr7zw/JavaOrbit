package de.tr7zw.javaorbit.server.player;

import java.util.concurrent.atomic.AtomicInteger;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Clan {

	@Getter private static final AtomicInteger counteter = new AtomicInteger(1000);

	private int id;
	private String name;
	private String tag;
	
}
