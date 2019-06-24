package de.tr7zw.javaorbit.server.maps;

import java.util.function.Function;
import de.tr7zw.javaorbit.server.Location;
import de.tr7zw.javaorbit.server.npc.EntityNPC;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EntityTarget{

    private Class<? extends EntityNPC> entityClass;
    private Function<Location, ? extends EntityNPC> creator;

}