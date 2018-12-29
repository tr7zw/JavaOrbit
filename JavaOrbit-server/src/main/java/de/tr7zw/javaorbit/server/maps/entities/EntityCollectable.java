package de.tr7zw.javaorbit.server.maps.entities;

import java.util.concurrent.atomic.AtomicInteger;

import de.tr7zw.javaorbit.server.Location;
import de.tr7zw.javaorbit.server.enums.collectables.Collectable;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class EntityCollectable implements Entity{

	private static AtomicInteger ID_COUNTER = new AtomicInteger(-1);
	private int id = ID_COUNTER.decrementAndGet();
	@NonNull private Collectable type;
	@NonNull private Location location;
	
}
