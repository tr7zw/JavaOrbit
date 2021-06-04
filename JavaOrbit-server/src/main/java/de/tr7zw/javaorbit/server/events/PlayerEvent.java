package de.tr7zw.javaorbit.server.events;

import de.tr7zw.javaorbit.server.maps.MapInstance;
import de.tr7zw.javaorbit.server.player.Player;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PlayerEvent {

    public Player player;
    public MapInstance instance;
    
}
