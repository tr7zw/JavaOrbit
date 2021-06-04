package de.tr7zw.javaorbit.server.maps.entities;

import java.util.concurrent.atomic.AtomicInteger;

import de.tr7zw.javaorbit.server.GateTarget;
import de.tr7zw.javaorbit.server.Location;
import de.tr7zw.javaorbit.server.enums.Gate;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class EntityGate implements EntityLegacy{

	private static AtomicInteger COUNTER = new AtomicInteger(0);
	private int id = COUNTER.incrementAndGet();
	@NonNull private Gate gate;
	@NonNull private Location location;
	@NonNull private GateTarget target;

}
